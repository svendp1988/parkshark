package south.park.parkshark.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import south.park.parkshark.domain.exceptions.InvalidEmailException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    @ExceptionHandler(InvalidEmailException.class)
    protected void invalidEmailException(InvalidEmailException ex, HttpServletResponse response) throws IOException {
        LOGGER.error("Email is not valid!", ex);
        response.sendError(HttpStatus.I_AM_A_TEAPOT.value(), ex.getMessage());
    }

}
