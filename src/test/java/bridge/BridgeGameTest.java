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
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.List;
import java.util.stream.Stream;

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

    void moveByRoute(BridgeGame bridgeGame, List<Square> route) {
        for (Square square : route) {
            bridgeGame.move(square);
        }
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

    @ParameterizedTest(name = "isSuccessTest Case {index}")
    @ArgumentsSource(SuccessTestData.class)
    void isSuccessTest(List<Square> route, boolean expected) {
        BridgeGame bridgeGame = new BridgeGame(mockBridgeMakerAdapter(), 3);
        moveByRoute(bridgeGame, route);

        boolean result = bridgeGame.isSuccess();
        assertThat(result).isEqualTo(expected);
    }

    static class SuccessTestData implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(CORRECT_ROUTE, true),
                    Arguments.of(List.of(), false),
                    Arguments.of(List.of(Square.UP, Square.UP, Square.UP), false)
            );
        }
    }
}
