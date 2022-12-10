package bridge.view.output;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.constant.Square;
import bridge.dto.BridgeGameDto;
import org.junit.jupiter.api.Test;
import java.util.List;

public class OutputViewTest extends PrintTestTool{

    private static final BridgeGameDto TEST_DATA_PROGRESS = new BridgeGameDto(
            List.of(Square.UP, Square.DOWN, Square.DOWN), List.of(Square.UP, Square.DOWN), 2, false);
    private static final BridgeGameDto TEST_FAIL = new BridgeGameDto(
            List.of(Square.UP, Square.DOWN, Square.DOWN), List.of(Square.UP, Square.UP), 2, false);

    @Test
    void printMapTest_InProgress() {
        BridgeGameDto dto = TEST_DATA_PROGRESS;

        new OutputView().printMap(dto);

        assertThat(output()).contains(
                "[ O |   ]",
                "[   | O ]"
        );
    }

    @Test
    void printMapTest_Fail() {
        BridgeGameDto dto = TEST_FAIL;

        new OutputView().printMap(dto);

        assertThat(output()).contains(
                "[ O | X ]",
                "[   |   ]"
        );
    }
}
