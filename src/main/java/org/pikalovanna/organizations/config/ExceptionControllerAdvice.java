package org.pikalovanna.organizations.config;

import lombok.extern.log4j.Log4j2;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.pikalovanna.organizations.dto.ExceptionResponse;
import org.pikalovanna.organizations.exceptions.ObjectNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.stream.Collectors;

@Log4j2
@Primary
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(ClientAbortException.class)
    public void handleClientAbortException(ClientAbortException e) {
        log.warn(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> handle(Throwable ex) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        String message = rootCause.getMessage();

        if (StringUtils.containsIgnoreCase(message, "Broken pipe"))
            return null;

        if (StringUtils.trimToEmpty(message).isEmpty())
            message = "Ошибка сервиса";

        String path = "";
        String method = "";

        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes) {
            path = ((ServletRequestAttributes) attributes).getRequest().getRequestURI();
            method = ((ServletRequestAttributes) attributes).getRequest().getMethod();
        }

        HttpStatus status = status(ex);
        String logMessage = String.format("%s %s - %s", method, path, ex.toString());

        if (status != HttpStatus.INTERNAL_SERVER_ERROR)
            log.warn(logMessage);
        else
            log.error(logMessage, ex);

        if (ex instanceof BindException)
            message = getResult(((BindException) ex).getBindingResult());
        else if (ex instanceof ObjectNotFoundException)
            message = ex.getMessage();

        return ResponseEntity.status(status)
                .body(new ExceptionResponse(status.value(), message, path));
    }

    private HttpStatus status(Throwable ex) {
        if (ex instanceof ObjectNotFoundException)
            return HttpStatus.NOT_FOUND;

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String getResult(BindingResult result) {
        return result.getAllErrors()
                .stream()
                .map(e -> String.format("%s: %s", ((FieldError) e).getField(), e.getDefaultMessage()))
                .collect(Collectors.joining(", "));
    }
}
