package org.ait.project.template.modules.users.service.internal.impl;

import lombok.RequiredArgsConstructor;
//import org.ait.project.template.modules.auth.dto.request.SignupRequest;
import org.ait.project.template.modules.users.dto.request.AuthDto;
import org.ait.project.template.modules.users.dto.request.CreateUserDto;
import org.ait.project.template.modules.users.dto.request.UpdateUserDto;
import org.ait.project.template.modules.users.dto.response.DeleteResponse;
import org.ait.project.template.modules.users.dto.response.TokenResponse;
import org.ait.project.template.modules.users.dto.response.UserResponse;
import org.ait.project.template.modules.users.exception.EmailExistsException;
import org.ait.project.template.modules.users.exception.EmailNotFoundException;
import org.ait.project.template.modules.users.model.entity.Users;
import org.ait.project.template.modules.users.service.internal.UserService;
import org.ait.project.template.modules.users.service.delegate.UserDelegate;
import org.ait.project.template.modules.users.transform.UserTransform;
import org.ait.project.template.shared.enums.ResponseEnum;
import org.ait.project.template.shared.template.ResponseCollection;
import org.ait.project.template.shared.template.ResponseDetail;
import org.ait.project.template.shared.template.ResponseTemplate;
import org.ait.project.template.shared.utils.JwtUtil;
import org.ait.project.template.shared.utils.PasswordEncryption;
import org.ait.project.template.shared.utils.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ResponseHelper responseHelper;

    private final UserDelegate userDelegate;

    private final UserTransform userTransform;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtUtil jwtUtil;

    public ResponseEntity<ResponseTemplate<ResponseCollection<UserResponse>>> getAllUsers() {
        List<Users> usersList = userDelegate.getAllUsers();

        return responseHelper.createResponseCollection(ResponseEnum.SUCCESS, null,
                userTransform.createUserResponseList(usersList));
    }

    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> createUser(CreateUserDto createUserDto) {
        System.out.println("email"+ createUserDto.getEmail());
//        Users checkUserByEmail = userDelegate.getUserByEmail(createUserDto.getEmail());

        Boolean userExist = userDelegate.findByEmail(createUserDto.getEmail());
        if (userExist) {
            throw new EmailExistsException();
        }

        String hashPassword = bCryptPasswordEncoder.encode(createUserDto.getPassword());
        createUserDto.setPassword(hashPassword);

        Users result = userDelegate.createUser(createUserDto);
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userTransform.createUserResponse(result));
//        return userDelegate.createUser(createUserDto);
    }

    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> getUserById(Integer id) {
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userTransform.createUserResponse(userDelegate.getUserById(id)));
    }

    public ResponseEntity<ResponseTemplate<ResponseDetail<DeleteResponse>>> deleteUser(Integer id) {
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userTransform.deleteUserReponse(userDelegate.deleteUser(id)));
    }

    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> updateUser(UpdateUserDto updateUserDto, Integer id) {
        String hashPassword = bCryptPasswordEncoder.encode(updateUserDto.getPassword());
        updateUserDto.setPassword(hashPassword);

        Users result = userDelegate.updateUser(updateUserDto, id);
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userTransform.createUserResponse(result));
    }

    public ResponseEntity<ResponseTemplate<ResponseDetail<TokenResponse>>> signIn(AuthDto authDto) {
        Users user = userDelegate.getUserByEmail(authDto.getEmail());

        if (user == null) {
            throw new EmailNotFoundException();
        }

//        String hashPassword = bCryptPasswordEncoder.encode(authDto.getPassword());

        System.out.println("plainpass" + authDto.getPassword());
        System.out.println("hashpas" + user.getPassword());

        Boolean match = bCryptPasswordEncoder.matches(authDto.getPassword(), user.getPassword());

        if (!match) {
            throw new RuntimeException("Invalid email/password");
        }

        String token = jwtUtil.generateToken(authDto.getEmail());
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);

        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, tokenResponse);
    }
}
