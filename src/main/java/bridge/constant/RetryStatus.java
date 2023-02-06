package bridge.constant;

import bridge.exception.ErrorMessage;

public enum RetryStatus {
    Retry("R"),
    Quit("Q");

    private final String format;

    RetryStatus(String format) {
        this.format = format;
    }

    public static RetryStatus getRetryStatus(String format) {
        for (RetryStatus retry : Retry.values()) {
            if (retry.format.equals(format)) {
                return retry;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INPUT_RETRY_STATUS.getMessage()); // 에러 메세지 설정
    }

    public String getFormat() {
        return format;
    }
}
