package org.ait.project.template.modules.reqres.service.internal;

import org.ait.project.template.modules.reqres.dto.request.CreateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.request.UpdateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.response.UpdateReqresResponse;
import org.ait.project.template.modules.reqres.dto.response.UserReqresResponse;
import org.ait.project.template.modules.reqres.model.entity.UserReqres;
import org.ait.project.template.modules.reqres.model.entity.UserReqresJob;
import org.ait.project.template.shared.template.ResponseCollection;
import org.ait.project.template.shared.template.ResponseDetail;
import org.ait.project.template.shared.template.ResponseList;
import org.ait.project.template.shared.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;

public interface UserReqresService {

    ResponseEntity<ResponseTemplate<ResponseCollection<UserReqres>>> getAllUsers();

    ResponseEntity<ResponseTemplate<ResponseDetail<UserReqresJob>>> addReqresJob(CreateUserReqresJob createUserReqresJob);

    ResponseEntity<ResponseTemplate<ResponseDetail<UserReqres>>> getUserById(Integer id);

    ResponseEntity<ResponseTemplate<ResponseDetail<UpdateReqresResponse>>> updateUserReqresJob(UpdateUserReqresJob updateUserReqresJob, Integer id);
}
