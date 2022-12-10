package bridge;

import bridge.util.Moving;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public List<String> makeBridge(int size) {
        return IntStream.range(0, size)
                .map(i -> bridgeNumberGenerator.generate())
                .mapToObj(flag -> Moving.findMoving(flag).code())
                .collect(Collectors.toList());
    }
}
