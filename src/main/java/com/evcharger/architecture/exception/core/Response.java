package com.evcharger.architecture.exception.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Response {
    protected boolean result;
    protected int status;
    protected String error_code;
    protected String msg;
    protected Date timestamp;
    protected String details;
}
