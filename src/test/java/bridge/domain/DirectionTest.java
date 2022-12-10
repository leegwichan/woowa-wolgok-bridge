package bridge.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @DisplayName("0을 입력하면 D, 1을 입력하면 U를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0:D", "1:U"}, delimiter = ':')
    void findMarkByCode(int code, String expected) {
        String result = Direction.findMarkByCode(code);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}