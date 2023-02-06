package bridge.helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import bridge.BridgeMaker;
import bridge.constant.Square;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BridgeMakerAdapterTest {

    @Test
    void makeBridgeTest() {
        BridgeMaker mockBridgeMaker = mock(BridgeMaker.class);
        when(mockBridgeMaker.makeBridge(anyInt())).thenReturn(List.of("U", "U", "D"));
        BridgeMakerAdapter adapter = new BridgeMakerAdapter(mockBridgeMaker);
        List<Square> expected = List.of(Square.UP, Square.UP, Square.DOWN);

        List<Square> result = adapter.makeBridge(3);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void makeBridgeTest_ThrowException() {
        BridgeMaker mockBridgeMaker = mock(BridgeMaker.class);
        when(mockBridgeMaker.makeBridge(anyInt())).thenReturn(List.of("U", "C", "D"));
        BridgeMakerAdapter adapter = new BridgeMakerAdapter(mockBridgeMaker);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> adapter.makeBridge(3));
        assertThat(exception.getMessage()).isEqualTo("[ERROR] 다리가 정상적으로 생성되지 않습니다. 관리자에게 문의하세요.");
    }
}
