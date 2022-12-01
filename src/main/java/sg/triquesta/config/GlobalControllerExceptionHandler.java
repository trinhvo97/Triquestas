package sg.triquesta.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;
import org.webjars.NotFoundException;
import sg.triquesta.constants.Commons;
import sg.triquesta.exception.BadRequestException;
import sg.triquesta.exception.FileStorageException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiCallError<String>> handleNotFoundException(HttpServletRequest request,
                                                                        NotFoundException ex) {
        log.error(Commons.NOT_FOUND_EXCEPTION_LOG, request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiCallError<>(HttpStatus.NOT_FOUND.value()
                        , Commons.NOT_FOUND_EXCEPTION, Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiCallError<String>> handleValidationException(HttpServletRequest request,
                                                                          ValidationException ex) {
        log.error(Commons.VALIDATION_EXCEPTION_LOG, request.getRequestURI(), ex);

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>(HttpStatus.BAD_REQUEST.value(),
                        Commons.VALIDATION_EXCEPTION, Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiCallError<String>> handleBindException(HttpServletRequest request,
                                                                    BindException ex) {
        log.error(Commons.BIND_EXCEPTION_LOG, request.getRequestURI(), ex);

        return ResponseEntity
                .badRequest()
                .body(new ApiCallError<>(HttpStatus.BAD_REQUEST.value(),
                        Commons.BIND_EXCEPTION,
                        ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiCallError<String>> handleAccessDeniedException(HttpServletRequest request,
                                                                            AccessDeniedException ex) {
        log.error(Commons.HANDLE_ACCESS_DENIED_EXCEPTION_LOG, request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiCallError<>(HttpStatus.FORBIDDEN.value(),
                        Commons.HANDLE_ACCESS_DENIED_EXCEPTION, Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler({SQLException.class, IOException.class})
    public ResponseEntity<ApiCallError<String>> handleSqlException(SQLException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiCallError<>(HttpStatus.BAD_REQUEST.value()
                        ,exception.getMessage(), Collections.singletonList(exception.getMessage())));
    }

    @ExceptionHandler(value = {BadRequestException.class, FileStorageException.class})
    public ResponseEntity<ApiCallError<String>> handleBadRequestException(HttpServletRequest request, BadRequestException ex) {
        log.error(Commons.BAD_REQUEST_EXCEPTION_LOG, request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiCallError<>(HttpStatus.BAD_REQUEST.value(),
                        Commons.BAD_REQUEST_EXCEPTION, Collections.singletonList(ex.getMessage())));
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class, ServerErrorException.class})
    public ResponseEntity<ApiCallError<String>> handleInternalServerError(HttpServletRequest request, Exception ex) {
        log.error(Commons.INTERNAL_SERVER_EXCEPTION_LOG, request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiCallError<>(HttpStatus.INTERNAL_SERVER_ERROR.value()
                        , Commons.INTERNAL_SERVER_EXCEPTION
                        , Collections.singletonList(ex.getMessage())));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiCallError<T> {
        private Integer status;
        private String message;
        private List<T> details;
    }
}
