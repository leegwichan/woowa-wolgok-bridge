package bridge.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("Q는 게임 종료를 의미한다.")
    @Test
    void createQuitCommand() {
        Command command = Command.from("Q");
        boolean result = command.isQuit();
        Assertions.assertThat(result).isTrue();
    }
}