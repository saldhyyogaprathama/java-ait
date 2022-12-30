package org.ait.project.template.modules.users.transform;

import org.ait.project.template.modules.users.dto.request.CreateUserDto;
import org.ait.project.template.modules.users.dto.request.UpdateUserDto;
import org.ait.project.template.modules.users.dto.response.DeleteResponse;
import org.ait.project.template.modules.users.dto.response.TokenResponse;
import org.ait.project.template.modules.users.dto.response.UserResponse;
import org.ait.project.template.modules.users.model.entity.Users;
import org.mapstruct.*;
import org.springframework.boot.context.properties.bind.Name;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserTransform {

//    @Mapping(target = "password", ignore = true)
    @Named("createUserResponse")
    UserResponse createUserResponse(Users users);

    @IterableMapping(qualifiedByName = "createUserResponse")
    List<UserResponse> createUserResponseList(List<Users> usersList);

    @Named("deleteResponse")
    DeleteResponse deleteUserReponse(DeleteResponse deleteResponse);

    Users createUserDtoToUser(CreateUserDto users);

    void updateUserDtoToUser(@MappingTarget Users users,UpdateUserDto updateUserDto);

//    TokenResponse tokenResponse(String token);
}
