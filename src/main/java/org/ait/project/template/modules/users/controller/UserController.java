package org.ait.project.template.modules.users.controller;

import lombok.RequiredArgsConstructor;
//import org.ait.project.template.modules.auth.dto.request.SignupRequest;
import org.ait.project.template.modules.users.dto.request.AuthDto;
import org.ait.project.template.modules.users.dto.request.CreateUserDto;
import org.ait.project.template.modules.users.dto.request.UpdateUserDto;
import org.ait.project.template.modules.users.dto.response.DeleteResponse;
import org.ait.project.template.modules.users.dto.response.TokenResponse;
import org.ait.project.template.modules.users.dto.response.UserResponse;
import org.ait.project.template.modules.users.service.internal.UserService;
import org.ait.project.template.shared.template.ResponseCollection;
import org.ait.project.template.shared.template.ResponseDetail;
import org.ait.project.template.shared.template.ResponseList;
import org.ait.project.template.shared.template.ResponseTemplate;
import org.ait.project.template.shared.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.authentication.AuthenticationManager;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserService {
    private final UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Override
    @GetMapping
    public ResponseEntity<ResponseTemplate<ResponseCollection<UserResponse>>> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> getUserById(
            @PathVariable Integer id
    ) {
        return userService.getUserById(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> createUser(
            @Valid
            @RequestBody CreateUserDto createUserDto
            ) {
        return userService.createUser(createUserDto);
    }


    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseTemplate<ResponseDetail<DeleteResponse>>> deleteUser(
            @PathVariable Integer id
    ) {
        return userService.deleteUser(id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserResponse>>> updateUser(
            @RequestBody UpdateUserDto updateUserDto,
            @PathVariable("id") Integer id
    ) {
        return userService.updateUser(updateUserDto, id);
    }

    @Override
    @PostMapping("/signin")
    public ResponseEntity<ResponseTemplate<ResponseDetail<TokenResponse>>> signIn(@RequestBody AuthDto authDto) {
        return userService.signIn(authDto);
    }
}
