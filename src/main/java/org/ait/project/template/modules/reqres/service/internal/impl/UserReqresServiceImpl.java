package org.ait.project.template.modules.reqres.service.internal.impl;

import lombok.RequiredArgsConstructor;
import org.ait.project.template.modules.reqres.dto.request.CreateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.request.UpdateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.response.UpdateReqresResponse;
import org.ait.project.template.modules.reqres.dto.response.UserReqresJobResponse;
import org.ait.project.template.modules.reqres.dto.response.UserReqresResponse;
import org.ait.project.template.modules.reqres.model.entity.UserReqres;
import org.ait.project.template.modules.reqres.model.entity.UserReqresJob;
import org.ait.project.template.modules.reqres.service.delegate.UserReqresDelegate;
import org.ait.project.template.modules.reqres.service.internal.UserReqresService;
import org.ait.project.template.modules.reqres.transform.ReqresTransform;
import org.ait.project.template.modules.users.exception.UserNotFoundException;
import org.ait.project.template.modules.users.model.entity.Users;
import org.ait.project.template.shared.enums.ResponseEnum;
import org.ait.project.template.shared.openfeign.reqres.ReqresClient;
import org.ait.project.template.shared.template.ResponseCollection;
import org.ait.project.template.shared.template.ResponseDetail;
import org.ait.project.template.shared.template.ResponseTemplate;
import org.ait.project.template.shared.utils.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReqresServiceImpl implements UserReqresService {

    private final UserReqresDelegate userDelegate;

    private final ReqresClient client;

    private final ReqresTransform reqresTransform;

    private final ResponseHelper responseHelper;

    /**
     * Function get all User Reqres
     * @return ResponseEntity which contains a list of UserReqres Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseCollection<UserReqres>>> getAllUsers() {
        List<UserReqres> userReqresFromDb = userDelegate.getAllUsers();
//        List<ReqresResponseDetail> result = null;

        if (userReqresFromDb.isEmpty()) {
            System.out.println("masuk");
            UserReqresResponse userReqresFromApi = client.getAllUsers();

            System.out.println("data from api" + userReqresFromApi);
            if (userReqresFromApi != null) {
                 userReqresFromDb = userDelegate.saveAll(reqresTransform.createReqresResponseList(userReqresFromApi.getData()));
            }
        }

        return responseHelper.createResponseCollection(ResponseEnum.SUCCESS,null, userReqresFromDb);
    }

    /**
     * Function to update UserReqres
     * @param createUserReqresJob is the body of the request that represents the data to be added
     * @return ResponseEntity which contains a list of UserReqresJob Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserReqresJob>>> addReqresJob(CreateUserReqresJob createUserReqresJob) {
        try {
            System.out.println("req.body " + createUserReqresJob);
            UserReqresJobResponse userReqresJobResult = client.addReqresJob(createUserReqresJob);

//            Integer id = Integer.parseInt(userReqresJobResult.getId());

            UserReqresJob saveUserReqresJob = userDelegate.save(reqresTransform.createEntityUserReqresJob(userReqresJobResult));

            return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, reqresTransform.createReqresJobResponse(saveUserReqresJob));
        } catch (Exception ex) {
            throw new RuntimeException("exception" + ex);
        }
    }

    /**
     * Function get Detail UserReqres
     * * @param id is id userreqres
     * @return ResponseEntity which contains a list of UserReqres Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseDetail<UserReqres>>> getUserById(Integer id) {
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, userDelegate.getUserById(id));
    }

    /**
     * Function to update UserReqres
     * @param updateUserReqresJob is the body of the request that represents the data to be updated
     * @param id is id userreqres
     * @return ResponseEntity which contains a list of UpdateReqresResponse Responses
     */
    public ResponseEntity<ResponseTemplate<ResponseDetail<UpdateReqresResponse>>> updateUserReqresJob(UpdateUserReqresJob updateUserReqresJob, Integer id) {
        Optional<UserReqresJob> user = userDelegate.getUserReqresJobById(id);

        UserReqresJob result = null;

        if (user.isPresent()) {
            UserReqresJob data = reqresTransform.updateReqresToEntity(updateUserReqresJob);
            result = userDelegate.updateUserReqresJob(data, id);

            return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, reqresTransform.updateUserReqresJobResponse(result));
        }

        System.out.println("data"+updateUserReqresJob);
        UpdateReqresResponse updateFromApi = client.updateReqresJob(updateUserReqresJob);
        System.out.println("updatefrom api" + updateFromApi);
        return responseHelper.createResponseDetail(ResponseEnum.SUCCESS, updateFromApi);
    }

}
