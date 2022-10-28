package be.sloth.ooorder.api;

import be.sloth.ooorder.service.exception.BadCredentialsException;
import be.sloth.ooorder.service.exception.NoPrivilegeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
public class OoorderExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    protected void badRequest(IllegalArgumentException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage());
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected void badCredentials(BadCredentialsException ex, HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(NoPrivilegeException.class)
    protected void badCredentials(NoPrivilegeException ex, HttpServletResponse response) throws IOException {
        response.sendError(FORBIDDEN.value(), ex.getMessage());
    }
}
