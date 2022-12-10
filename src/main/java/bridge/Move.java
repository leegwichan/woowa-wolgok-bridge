package bridge;

import java.util.Arrays;

public enum Move {
    UP(1, "U"),
    DOWN(0, "D");

    private final int flag;
    private final String code;

    Move(int flag, String code) {
        this.flag = flag;
        this.code = code;
    }

    public static Move findMove(int flag) {
        return Arrays.stream(Move.values())
                .filter(move -> move.flag == flag)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public static Move findMove(String code) {
        return Arrays.stream(Move.values())
                .filter(move -> move.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public static boolean isMove(String code) {
        return Arrays.stream(Move.values())
                .anyMatch(move -> move.code.equals(code));
    }

    public String getCode() {
        return code;
    }
}
