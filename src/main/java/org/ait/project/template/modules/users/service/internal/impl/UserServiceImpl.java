package org.ait.project.template.modules.users.service.internal.impl;

import lombok.RequiredArgsConstructor;
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
import org.ait.project.template.shared.utils.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ResponseHelper responseHelper;

    private final UserDelegate userDelegate;

    private final UserTransform userTransform;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtUtil jwtUtil;

    /**
     * Function to get all users
     * @return ResponseEntity which contains a list of UserResponse Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseCollection<UserResponse>>> getAllUsers() {
        List<Users> usersList = userDelegate.getAllUsers();

        return responseHelper.createResponseCollection(ResponseEnum.SUCCESS, null,
                userTransform.createUserResponseList(usersList));
    }

    /**
     * Function to create user
     * @param createUserDto is the body of the request that represents the data to be added
     * @return ResponseEntity which contains a list of UserResponse Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> createUser(CreateUserDto createUserDto) {

        Boolean userExist = userDelegate.findByEmail(createUserDto.getEmail());

        if (userExist) {
            throw new EmailExistsException();
        }

        String hashPassword = bCryptPasswordEncoder.encode(createUserDto.getPassword());
        createUserDto.setPassword(hashPassword);

        Users result = userDelegate.createUser(userTransform.createUserDtoToUser(createUserDto));
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userTransform.createUserResponse(result));
    }

    /**
     * Function to get detail users
     * @param id is id user
     * @return ResponseEntity which contains a list of UserResponse Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> getUserById(Integer id) {
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userTransform.createUserResponse(userDelegate.getUserById(id)));
    }

    /**
     * Function to delete user
     * @param id is id user
     * @return ResponseEntity which contains a list of DeleteResponse Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseDetail<DeleteResponse>>> deleteUser(Integer id) {
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userTransform.deleteUserReponse(userDelegate.deleteUser(id)));
    }

    /**
     * Function to update user
     * @param updateUserDto is the body of the request that represents the data to be updated
     * @param id is id user
     * @return ResponseEntity which contains a list of UserResponse Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> updateUser(UpdateUserDto updateUserDto, Integer id) {
        String hashPassword = bCryptPasswordEncoder.encode(updateUserDto.getPassword());
        updateUserDto.setPassword(hashPassword);

        Users result = userDelegate.updateUser(updateUserDto, id);
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userTransform.createUserResponse(result));
    }

    /**
     * Function to get token login
     * @param authDto is the body of the request that represents email and password
     * @return ResponseEntity which contains a list of TokenResponse Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseDetail<TokenResponse>>> signIn(AuthDto authDto) {
        Users user = userDelegate.getUserByEmail(authDto.getEmail());

        if (user == null) {
            throw new EmailNotFoundException();
        }

//        String hashPassword = bCryptPasswordEncoder.encode(authDto.getPassword());

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
