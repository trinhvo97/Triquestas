package sg.triquesta.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Commons {
    public static final String NOT_FOUND_EXCEPTION_LOG =  "NotFoundException {}\n";
    public static final String NOT_FOUND_EXCEPTION =  "NotFoundException ";
    public static final String VALIDATION_EXCEPTION_LOG = "ValidationException {}\n";
    public static final String VALIDATION_EXCEPTION = "ValidationException ";
    public static final String BIND_EXCEPTION_LOG = "BindException {}\n";
    public static final String BIND_EXCEPTION = "BindException ";
    public static final String HANDLE_ACCESS_DENIED_EXCEPTION_LOG = "handleAccessDeniedException {}\n";
    public static final String HANDLE_ACCESS_DENIED_EXCEPTION = "Access denied!";
    public static final String BAD_REQUEST_EXCEPTION_LOG = "Bad request {}\n";
    public static final String BAD_REQUEST_EXCEPTION = "Bad request! ";
    public static final String INTERNAL_SERVER_EXCEPTION_LOG = "handleInternalServerError {}\n";
    public static final String INTERNAL_SERVER_EXCEPTION = "Internal server error ";
    public static final String INVALID_FILE_NAME = "Internal server error ";

}
