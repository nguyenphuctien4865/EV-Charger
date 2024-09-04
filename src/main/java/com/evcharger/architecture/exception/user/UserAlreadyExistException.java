package com.evcharger.architecture.exception.user;

import com.evcharger.architecture.exception.core.ArchitectureException;
import com.evcharger.architecture.util.Constants;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends ArchitectureException {

    //region
    private static final long serialVersionUID = 1L;
    //endregion

    public UserAlreadyExistException() {
        super();
        this.code = Constants.Exception.User.USER_ALREADY_EXIST_CODE;
        this.msg = Constants.Exception.User.USER_ALREADY_EXIST;
        this.status = HttpStatus.BAD_REQUEST;
    }
}