package bridge.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Bridge {

    private final List<Floor> bridge;

    private Bridge(List<String> bridge) {
        this.bridge = bridge.stream()
                .map(Floor::from)
                .collect(Collectors.toUnmodifiableList());

    }

    public static Bridge from(List<String> bridge) {
        return new Bridge(bridge);
    }

    public boolean canMove(Player player, Direction direction) {
        return bridge.get(player.getPosition()).equals(direction.getMark());
    }
    }

    public Floor getNowBridge(Player player) {
        return bridge.get(player.getPosition());
    }
}