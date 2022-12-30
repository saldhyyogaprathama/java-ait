package org.ait.project.template.modules.users.transform;

import org.ait.project.template.modules.users.dto.request.CreateUserDto;
import org.ait.project.template.modules.users.model.entity.Users;
import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper(componentModel = "spring")
public interface UserMapStructMapper {

//    @Mapping(target = "password", ignore = true)
    Users createUserDtoToUser(CreateUserDto users);
}
