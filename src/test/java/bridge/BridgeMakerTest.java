package bridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

public class BridgeMakerTest {

    @Test
    void makeBridgeTest() {
        BridgeNumberGenerator mockGenerator = mock(BridgeNumberGenerator.class);
        when(mockGenerator.generate()).thenReturn(0,0,0,1,1);
        BridgeMaker bridgeMaker = new BridgeMaker(mockGenerator);
        List<String> expected = List.of("D", "D", "D", "U", "U");

        List<String> result = bridgeMaker.makeBridge(5);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void makeBridgeTest_throwExceptionByGenerator() {
        BridgeNumberGenerator mockGenerator = mock(BridgeNumberGenerator.class);
        when(mockGenerator.generate()).thenReturn(0,0,0,1,3);
        BridgeMaker bridgeMaker = new BridgeMaker(mockGenerator);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> bridgeMaker.makeBridge(5));
        assertThat(exception.getMessage())
                .isEqualTo("[ERROR] 다리가 정상적으로 생성되지 않습니다. 관리자에게 문의하세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {"21", "2"})
    void makeBridgeTest_throwExceptionBySize(int bridgeSize) {
        BridgeNumberGenerator mockGenerator = mock(BridgeNumberGenerator.class);
        when(mockGenerator.generate()).thenReturn(0,0,0,1,1);
        BridgeMaker bridgeMaker = new BridgeMaker(mockGenerator);

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> bridgeMaker.makeBridge(bridgeSize));
        assertThat(exception.getMessage())
                .isEqualTo("[ERROR] 다리 길이는 3부터 20 사이의 숫자여야 합니다.");
    }
}
