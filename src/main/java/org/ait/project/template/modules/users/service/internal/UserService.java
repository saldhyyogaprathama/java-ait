package org.ait.project.template.modules.users.service.internal;

//import org.ait.project.template.modules.auth.dto.request.SignupRequest;
import org.ait.project.template.modules.users.dto.request.AuthDto;
import org.ait.project.template.modules.users.dto.request.CreateUserDto;
import org.ait.project.template.modules.users.dto.request.UpdateUserDto;
import org.ait.project.template.modules.users.dto.response.DeleteResponse;
import org.ait.project.template.modules.users.dto.response.TokenResponse;
import org.ait.project.template.modules.users.dto.response.UserResponse;
import org.ait.project.template.shared.template.ResponseCollection;
import org.ait.project.template.shared.template.ResponseDetail;
import org.ait.project.template.shared.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ResponseTemplate<ResponseCollection<UserResponse>>> getAllUsers();

    ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> getUserById(Integer id);

    ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> createUser(CreateUserDto createUserDto);

    ResponseEntity<ResponseTemplate<ResponseDetail<DeleteResponse>>> deleteUser(Integer id);

    ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> updateUser(UpdateUserDto updateUserDto, Integer id);

    ResponseEntity<ResponseTemplate<ResponseDetail<TokenResponse>>> signIn(AuthDto authDto);
//    TokenResponse signIn(AuthDto authDto);
//    ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> createUser(SignupRequest
//    signupRequest);
}
