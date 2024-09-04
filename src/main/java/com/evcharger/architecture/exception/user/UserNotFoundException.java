package com.evcharger.architecture.exception.user;

import com.evcharger.architecture.exception.core.ArchitectureException;
import com.evcharger.architecture.util.Constants;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ArchitectureException {

    //region
    private static final long serialVersionUID = 1L;
    //endregion

    public UserNotFoundException() {
        super();
        this.code = Constants.Exception.User.USER_NOT_FOUND_CODE;
        this.msg = Constants.Exception.User.USER_NOT_FOUND;
        this.status = HttpStatus.NOT_FOUND;
    }
}