package com.myapp.roombookingapp.controller.error;

import com.myapp.roombookingapp.dto.error.ErrorInfo;
import com.myapp.roombookingapp.exceptions.UserAlreadyExistsException;
import org.h2.jdbc.JdbcSQLDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Controller for returning custom error message if any exception occurred.
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    @Autowired
    private MessageSourceAccessor messageSource;

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorInfo handleBadCredentialsException(HttpServletRequest req, Exception ex) {
        log.error("Error occurred while requesting resource: {} : ", req.getRequestURI(), ex);
        return new ErrorInfo(req.getRequestURI(), HttpServletResponse.SC_UNAUTHORIZED, ex.getLocalizedMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorInfo handleInternalServerError(HttpServletRequest req, Exception ex) {
        String errorMessage = messageSource.getMessage("InternalServerError","An error occurred, please contact system administrator");
        log.error("Error occurred while requesting resource: {} : ", req.getRequestURI(), ex);
        return new ErrorInfo(req.getRequestURI(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorInfo handleUserAlreadyExistsError(HttpServletRequest req, Exception ex) {
        log.error("Error occurred while requesting resource: {} : ", req.getRequestURI(), ex);
        String errorMessage = messageSource.getMessage("UserAlreadyExistsException","User with this login already exists");
        return new ErrorInfo(req.getRequestURI(), HttpServletResponse.SC_CONFLICT, errorMessage);
    }


}
