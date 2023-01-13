package org.ait.project.template.modules.reqres.service.delegate;

import org.ait.project.template.modules.reqres.dto.request.UpdateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.response.ReqresResponseDetail;
import org.ait.project.template.modules.reqres.model.entity.UserReqres;
import org.ait.project.template.modules.reqres.model.entity.UserReqresJob;

import java.util.List;
import java.util.Optional;

public interface UserReqresDelegate {
    List<UserReqres> getAllUsers();
    List<UserReqres> saveAll(List<UserReqres> userReqresList);

    UserReqresJob save(UserReqresJob userReqresJob);

    UserReqres getUserById(Integer id);

    Optional<UserReqresJob> getUserReqresJobById(Integer id);

    UserReqresJob updateUserReqresJob(UserReqresJob userReqresJob, Integer id);
}
