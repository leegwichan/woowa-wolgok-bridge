package bridge.domain;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.util.Moving;
import java.util.List;
import java.util.stream.Collectors;

public class Bridge {
    private static final BridgeMaker BRIDGE_MAKER = new BridgeMaker(new BridgeRandomNumberGenerator());
    private final List<Moving> bridge;

    public Bridge(List<String> bridge) {
        this.bridge = bridge.stream().map(Moving::findMoving).collect(Collectors.toList());
    }

    public boolean isMovable(int position, Moving moving) {
        return bridge.get(position).equals(moving);
    }

    public static Bridge generateBridge(BridgeSize bridgeSize) {
        return new Bridge(BRIDGE_MAKER.makeBridge(bridgeSize.getSize()));
    }

    public int size() {
        return bridge.size();
    }
}
