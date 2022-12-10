package bridge.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BridgeSizeTest {

    @DisplayName("잘못된 숫자로 다리 길이를 생성할 경우 예외가 발생한다.")
    @ValueSource(ints = {-2, 2, 21, 100})
    @ParameterizedTest
    void testBridgeSizeOutOfRange(int input) {
        assertThatThrownBy(() -> new BridgeSize(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("적절한 숫자로 다리 길이를 생성할 경우 예외가 발생하지 않는다.")
    @ValueSource(ints = {3, 10, 20})
    @ParameterizedTest
    void testBridgeSizeInRange(int input) {
        assertThatNoException().isThrownBy(() -> new BridgeSize(input));
    }
}
