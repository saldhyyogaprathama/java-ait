package org.ait.project.template.modules.users.exception;

import lombok.extern.slf4j.Slf4j;
import org.ait.project.template.configuration.exception.ModuleException;
import org.ait.project.template.shared.enums.ResponseEnum;

@Slf4j
public class EmailNotFoundException extends ModuleException {

    public EmailNotFoundException() {
        super(ResponseEnum.EMAIL_NOT_FOUND);
        log.error("Email Not Found");
    }
}
