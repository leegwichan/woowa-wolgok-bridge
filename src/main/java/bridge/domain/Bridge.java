package bridge.domain;

import bridge.dto.BridgeStateDto;

import java.util.ArrayList;
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

    public boolean canMove(Player player, Floor direction) {
        return bridge.get(player.getPosition()).equals(direction);
    }

    public BridgeStateDto getNowBridgeState(Player player, boolean canMove) {
        return BridgeStateDto.of(bridge.subList(0, player.getPosition()+1), canMove);
    }
}