package bridge.exception;

public enum ErrorMessage {
    NOT_MATCH_SIGNATURE("다리가 정상적으로 생성되지 않습니다.");
    ;

    ErrorMessage(String message) {
        this.message = message;
    }

    private static final String PREFIX = "[ERROR] ";

    private String message;

    public String getMessage() {
        return PREFIX + message;
    }
}
