package org.ait.project.template.modules.users.service.delegate;

import org.ait.project.template.modules.users.dto.request.CreateUserDto;
import org.ait.project.template.modules.users.dto.request.UpdateUserDto;
import org.ait.project.template.modules.users.dto.response.DeleteResponse;
import org.ait.project.template.modules.users.model.entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserDelegate {
    List<Users> getAllUsers();
    Users getUserById(Integer id);

    Users getUserByEmail(String email);

    Boolean findByEmail(String email);

    Optional<Users> getUserByName(String name);

    Users createUser(Users users);

    DeleteResponse deleteUser(Integer id);

    Users updateUser(UpdateUserDto updateUserDto, Integer id);

}
