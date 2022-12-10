package bridge.exception;

public enum ErrorMessage {
    NOT_MATCH_SIGNATURE("다리가 정상적으로 생성되지 않습니다. 관리자에게 문의하세요."),
    BRIDGE_SIZE_ERROR("다리 길이는 3부터 20 사이의 숫자여야 합니다."),
    NOT_MOVE_BRIDGE("다리를 더 이상 건널 수 없습니다.")
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
