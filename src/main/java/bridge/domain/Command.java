package bridge.domain;

import java.util.Arrays;

public enum Command {
    RETRY("R"),
    QUIT("Q");

    private final String mark;

    Command(String mark) {
        this.mark = mark;
    }

    public static Command from(String mark) {
        return Arrays.stream(Command.values()).filter(command -> command.mark.equals(mark))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("R(재시작) 또는 Q(종료)를 입력해주세요."));
    }

    public boolean isQuit() {
        return this.equals(QUIT);
    }
}