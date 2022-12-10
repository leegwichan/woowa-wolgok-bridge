package bridge.util;

import java.util.Arrays;

public enum Moving {
    UP(1, "U"),
    DOWN(0, "D");

    private final int flag;
    private final String code;

    Moving(int flag, String code) {
        this.flag = flag;
        this.code = code;
    }

    public static Moving findMoving(int flag) {
        return Arrays.stream(Moving.values())
                .filter(move -> move.flag == flag)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public static Moving findMoving(String code) {
        return Arrays.stream(Moving.values())
                .filter(move -> move.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public String code() {
        return code;
    }
}
