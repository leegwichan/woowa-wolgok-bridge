package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bridge.util.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MapTest {
    private final Map map = new Map();

    @DisplayName("성공 지도 생성")
    @Test
    void testMapSuccess() {
        map.addMove(Move.UP,false);
        assertThat(map.toString()).isEqualTo("[ O ]\n[   ]");
        map.addMove(Move.DOWN,false);
        assertThat(map.toString()).isEqualTo("[ O |   ]\n[   | O ]");
    }

    @DisplayName("실패 지도 생성")
    @Test
    void testMapFail() {
        map.addMove(Move.UP,true);
        assertThat(map.toString()).isEqualTo("[ X ]\n[   ]");
    }
}
