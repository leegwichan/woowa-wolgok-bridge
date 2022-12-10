package bridge.util;

import java.util.Arrays;

public enum GameCommand {
    RETRY("R"),
    QUIT("Q");

    private final String code;

    GameCommand(String code) {
        this.code = code;
    }

    public static GameCommand findCommand(String code) {
        return Arrays.stream(GameCommand.values())
                .filter(command -> command.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public String code() {
        return code;
    }
}
