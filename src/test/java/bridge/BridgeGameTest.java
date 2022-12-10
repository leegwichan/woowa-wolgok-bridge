package bridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import bridge.constant.Square;
import bridge.dto.BridgeGameDto;
import bridge.helper.BridgeMakerAdapter;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BridgeGameTest {

    private static final List<Square> CORRECT_ROUTE = List.of(Square.UP, Square.UP, Square.DOWN);

    private BridgeMakerAdapter mockBridgeMakerAdapter() {
        BridgeMakerAdapter mockAdapter = mock(BridgeMakerAdapter.class);
        when(mockAdapter.makeBridge(anyInt())).thenReturn(CORRECT_ROUTE);
        return mockAdapter;
    }

    void moveCorrectRoute(BridgeGame bridgeGame) {
        bridgeGame.move(Square.UP);
        bridgeGame.move(Square.UP);
        bridgeGame.move(Square.DOWN);
    }

    @Test
    void moveTest() {
        BridgeGame bridgeGame = new BridgeGame(mockBridgeMakerAdapter(), 3);

        bridgeGame.move(Square.UP);
        BridgeGameDto result = bridgeGame.getBridgeGameDto();

        assertThat(result.getMovedSquare()).isEqualTo(List.of(Square.UP));
    }

    @Test
    void moveTest_ThrowExceptionByMovingSquare() {
        BridgeGame bridgeGame = new BridgeGame(mockBridgeMakerAdapter(), 3);

        bridgeGame.move(Square.DOWN);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> bridgeGame.move(Square.DOWN));
        assertThat(exception.getMessage())
                .isEqualTo("[ERROR] 다리를 더 이상 건널 수 없습니다.");
    }

    @Test
    void moveTest_ThrowExceptionByReachingEnd() {
        BridgeGame bridgeGame = new BridgeGame(mockBridgeMakerAdapter(), 3);
        moveCorrectRoute(bridgeGame);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> bridgeGame.move(Square.DOWN));
        assertThat(exception.getMessage())
                .isEqualTo("[ERROR] 다리를 더 이상 건널 수 없습니다.");
    }

    @Test
    void retryTest() {
        BridgeGame bridgeGame = new BridgeGame(mockBridgeMakerAdapter(), 3);
        bridgeGame.move(Square.UP);

        bridgeGame.retry();
        BridgeGameDto result = bridgeGame.getBridgeGameDto();

        assertThat(result.getMovedSquare()).isEqualTo(List.of());
        assertThat(result.getCountOfTry()).isEqualTo(2);
    }
}
