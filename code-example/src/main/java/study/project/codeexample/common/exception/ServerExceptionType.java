package study.project.codeexample.common.exception;

import org.springframework.http.HttpStatus;

public enum ServerExceptionType implements BaseExceptionType {

    INTERNAL_SERVER_ERROR("서버 내부 오류입니다.", 500, HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final int code;
    private final HttpStatus httpStatus;

    ServerExceptionType(String message,
                        int code,
                        HttpStatus httpStatus
    ) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
