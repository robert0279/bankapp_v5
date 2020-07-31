package com.app.bank.exception;

import org.springframework.core.NestedRuntimeException;

public class UserNotFindException extends NestedRuntimeException {

    public UserNotFindException(String message) {
        super(message);
    }

}
