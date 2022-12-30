package org.ait.project.template.modules.users.exception;

import lombok.extern.slf4j.Slf4j;
import org.ait.project.template.configuration.exception.ModuleException;
import org.ait.project.template.shared.enums.ResponseEnum;

@Slf4j
public class EmailExistsException extends ModuleException {

    public EmailExistsException() {
        super(ResponseEnum.EMAIL_ALREADY_EXIST);
        log.error("Email already exist");
    }
}
