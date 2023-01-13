package org.ait.project.template.shared.openfeign.reqres;

import org.ait.project.template.modules.reqres.dto.request.CreateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.request.UpdateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.response.UpdateReqresResponse;
import org.ait.project.template.modules.reqres.dto.response.UserReqresJobResponse;
import org.ait.project.template.modules.reqres.dto.response.UserReqresResponse;
import org.springframework.stereotype.Component;

@Component
public class ReqresClientFallback implements ReqresClient{
    @Override
    public UserReqresResponse getAllUsers() {
        return null;
    }

    @Override
    public UserReqresJobResponse addReqresJob(CreateUserReqresJob createUserReqresJob) {
        try {
//            return null;
        } catch (Exception ex) {
            System.out.println("ex " + ex);
        }
        return null;
    }

    @Override
    public UpdateReqresResponse updateReqresJob(UpdateUserReqresJob updateUserReqresJob) {
        try {
//            return null;
        } catch (Exception ex) {
            System.out.println("ex " + ex);
        }
        return null;
    }
}
