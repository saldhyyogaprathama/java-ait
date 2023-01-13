package org.ait.project.template.shared.openfeign.reqres;

import org.ait.project.template.modules.reqres.dto.request.CreateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.request.UpdateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.response.UpdateReqresResponse;
import org.ait.project.template.modules.reqres.dto.response.UserReqresJobResponse;
import org.ait.project.template.modules.reqres.dto.response.UserReqresResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(
        value = "reqres-client",
        url = "https://reqres.in/api",
        fallback = ReqresClientFallback.class

)
public interface ReqresClient {
    @GetMapping("/users")
    UserReqresResponse getAllUsers();

    @PostMapping("/users")
    UserReqresJobResponse addReqresJob(@RequestBody CreateUserReqresJob createUserReqresJob);

    @PutMapping("/users/2")
    UpdateReqresResponse updateReqresJob(@RequestBody UpdateUserReqresJob updateUserReqresJob);
}
