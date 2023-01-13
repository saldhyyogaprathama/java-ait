package org.ait.project.template.modules.reqres.controller;

import lombok.RequiredArgsConstructor;
import org.ait.project.template.modules.reqres.dto.request.CreateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.request.UpdateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.response.UpdateReqresResponse;
import org.ait.project.template.modules.reqres.model.entity.UserReqres;
import org.ait.project.template.modules.reqres.model.entity.UserReqresJob;
import org.ait.project.template.modules.reqres.service.internal.UserReqresService;
import org.ait.project.template.modules.users.dto.request.UpdateUserDto;
import org.ait.project.template.modules.users.dto.response.UserResponse;
import org.ait.project.template.shared.template.ResponseCollection;
import org.ait.project.template.shared.template.ResponseDetail;
import org.ait.project.template.shared.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users-reqres")
public class UserReqresController implements UserReqresService {

    private final UserReqresService userReqresService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseTemplate<ResponseCollection<UserReqres>>> getAllUsers() {
        return userReqresService.getAllUsers();
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserReqresJob>>> addReqresJob(
            @Valid
            @RequestBody CreateUserReqresJob createUserReqresJob) {
        return userReqresService.addReqresJob(createUserReqresJob);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserReqres>>> getUserById(
            @PathVariable Integer id
    ) {
        return userReqresService.getUserById(id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ResponseTemplate<ResponseDetail<UpdateReqresResponse>>> updateUserReqresJob(
            @RequestBody UpdateUserReqresJob updateUserReqresJob,
            @PathVariable("id") Integer id
    ) {
        return userReqresService.updateUserReqresJob(updateUserReqresJob, id);
    }
}
