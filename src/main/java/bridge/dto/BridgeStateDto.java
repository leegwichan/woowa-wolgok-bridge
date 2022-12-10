package bridge.dto;

import bridge.domain.Floor;

import java.util.List;
import java.util.stream.Collectors;

public class BridgeStateDto {
    private final List<String> bridgeState;
    private final boolean canMove;

    private BridgeStateDto(List<String> bridgeState, boolean canMove) {
        this.bridgeState = bridgeState;
        this.canMove = canMove;
    }

    public static BridgeStateDto of(List<Floor> bridge, boolean canMove) {
        return new BridgeStateDto(
                bridge.stream()
                        .map(Floor::getDirection)
                        .collect(Collectors.toUnmodifiableList())
                , canMove);
    }

    public List<String> getBridgeState() {
        return bridgeState.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isCanMove() {
        return canMove;
    }
}
