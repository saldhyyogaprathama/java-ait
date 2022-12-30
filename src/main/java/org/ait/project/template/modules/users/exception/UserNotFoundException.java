package org.ait.project.template.modules.users.exception;

import lombok.extern.slf4j.Slf4j;
import org.ait.project.template.configuration.exception.ModuleException;
import org.ait.project.template.shared.enums.ResponseEnum;

@Slf4j
public class UserNotFoundException extends ModuleException {

    public UserNotFoundException() {
        super(ResponseEnum.USER_NOT_FOUND);
        log.error("User Not Found");
    }
}
