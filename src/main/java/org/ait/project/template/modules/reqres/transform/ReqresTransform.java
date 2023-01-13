package org.ait.project.template.modules.reqres.transform;

import org.ait.project.template.modules.reqres.dto.request.UpdateUserReqresJob;
import org.ait.project.template.modules.reqres.dto.response.ReqresResponseDetail;
import org.ait.project.template.modules.reqres.dto.response.UpdateReqresResponse;
import org.ait.project.template.modules.reqres.dto.response.UserReqresJobResponse;
import org.ait.project.template.modules.reqres.model.entity.UserReqres;
import org.ait.project.template.modules.reqres.model.entity.UserReqresJob;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReqresTransform {

    @Named("createReqresResponse")
    UserReqres createReqresResponse(ReqresResponseDetail reqresResponseDetail);

//    @Named("reqresResponse")

    @IterableMapping(qualifiedByName = "createReqresResponse")
    List<UserReqres> createReqresResponseList(List<ReqresResponseDetail> reqresResponseDetails);

//    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping( target = "id", source = "id", qualifiedByName = "StringToInt")
    UserReqresJob createEntityUserReqresJob(UserReqresJobResponse userReqresJobResponse);

    UserReqresJob createReqresJobResponse(UserReqresJob userReqresJob);

    @Named("StringToInt")
    default Integer stringToInteger(String id) {
        Integer convertId = Integer.parseInt(id);
        return convertId;
    }

    @Named("updateReqresToEntity")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "job", source = "job")
    UserReqresJob updateReqresToEntity(UpdateUserReqresJob updateUserReqresJob);

    @Named("updateUserReqresJobResponse")
    UpdateReqresResponse updateUserReqresJobResponse(UserReqresJob userReqresJob);
}
