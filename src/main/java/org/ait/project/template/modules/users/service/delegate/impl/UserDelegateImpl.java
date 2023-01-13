package org.ait.project.template.modules.users.service.delegate.impl;

import lombok.RequiredArgsConstructor;
import org.ait.project.template.modules.users.dto.request.CreateUserDto;
import org.ait.project.template.modules.users.dto.request.UpdateUserDto;
import org.ait.project.template.modules.users.dto.response.DeleteResponse;
import org.ait.project.template.modules.users.dto.response.UserResponse;
import org.ait.project.template.modules.users.exception.EmailNotFoundException;
import org.ait.project.template.modules.users.exception.UserNotFoundException;
import org.ait.project.template.modules.users.model.entity.Users;
import org.ait.project.template.modules.users.model.repository.UsersRepository;
import org.ait.project.template.modules.users.service.delegate.UserDelegate;
//import org.ait.project.template.modules.users.transform.UserMapStructMapper;
import org.ait.project.template.modules.users.transform.UserTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDelegateImpl implements UserDelegate {
    private final UsersRepository repository;

    private final UserTransform userTransform;

    @Override
    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Users getUserById(Integer id) {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Users getUserByEmail(String email) {
        return (Users) repository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
    }

    @Override
    public Boolean findByEmail(String email) {
        Boolean exists = repository.existsByEmail(email);

        return exists;
    }

    @Override
    public Optional<Users> getUserByName(String name) {
        return Optional.ofNullable(repository.findByName(name).orElseThrow(EmailNotFoundException::new));
    }

    @Override
    public Users createUser(Users users) {
        Users result = repository.save(users);
//        System.out.println("result" + result);
//         repository.save(mapStructMapper.createUserDtoToUser(createUserDto));
        return result;
    }

    @Override
    public DeleteResponse deleteUser(Integer id) {
        Optional<Users> user = repository.findById(id);
         if (!user.isPresent()) {
            throw new UserNotFoundException();
         }
         repository.deleteById(id);
         DeleteResponse response = new DeleteResponse();
         response.setId(id);
         response.setMessage("User with id " + id + " is deleted");

         return response;
    }

    @Override
    public Users updateUser(UpdateUserDto updateUserDto, Integer id) {
        Users user = repository.findById(id).orElseThrow(UserNotFoundException::new);


        if (Objects.isNull(user)) {
           throw new UserNotFoundException();
        }
//
        if (Objects.nonNull(updateUserDto.getEmail())) {
            user.setEmail(updateUserDto.getEmail());
        }

        if (Objects.nonNull(updateUserDto.getName())) {
            user.setName(updateUserDto.getName());
        }

        if (Objects.nonNull(updateUserDto.getPassword())) {
            user.setPassword(updateUserDto.getPassword());
        }

        if (Objects.nonNull(updateUserDto.getPhonenumber())) {
            user.setPhonenumber(updateUserDto.getPhonenumber());
        }

        return repository.save(user);
    }
}
