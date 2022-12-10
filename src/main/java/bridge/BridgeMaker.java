package bridge;

import bridge.constant.Constant;
import bridge.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private static final int UP = 1;
    private static final int DOWN = 0;

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validateSize(size);
        List<String> bridge = new ArrayList<>();
        for (int count = 0; count < size; count++) {
            int decideNumber = bridgeNumberGenerator.generate();
            bridge.add(getSignature(decideNumber));
        }
        return bridge;
    }

    private void validateSize(int size) {
        if (size < 3 || size > 15) {
            throw new IllegalArgumentException(ErrorMessage.BRIDGE_SIZE_ERROR.getMessage());
        }
    }

    private String getSignature(int decideNumber) {
        if (decideNumber == UP) {
            return Constant.UP_SIGNATURE;
        }
        if (decideNumber == DOWN) {
            return Constant.DOWN_SIGNATURE;
        }
        throw new IllegalArgumentException(ErrorMessage.NOT_MATCH_SIGNATURE.getMessage());
    }
}
