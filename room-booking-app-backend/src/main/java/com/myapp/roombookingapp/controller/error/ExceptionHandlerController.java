package com.myapp.roombookingapp.controller.error;

import com.myapp.roombookingapp.dto.error.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for returning custom error message if any exception occurred.
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorInfo handleBadCredentialsException(HttpServletRequest req, Exception ex) {
        log.error("Error occurred while requesting resource: {} : {}", req.getRequestURI(), ex);
        return new ErrorInfo(req.getRequestURI(), HttpServletResponse.SC_UNAUTHORIZED, ex.getLocalizedMessage());
    }


}
