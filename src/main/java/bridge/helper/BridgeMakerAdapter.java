package bridge.helper;

import bridge.BridgeMaker;
import bridge.constant.Constant;
import bridge.constant.Square;
import bridge.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class BridgeMakerAdapter {

    private final BridgeMaker bridgeMaker;

    public BridgeMakerAdapter(BridgeMaker bridgeMaker) {
        this.bridgeMaker = bridgeMaker;
    }

    public List<Square> makeBridge(int size) {
        List<String> beforeFormBridge = bridgeMaker.makeBridge(size);
        List<Square> afterFormBridge = new ArrayList<>();
        for (int index = 0; index < beforeFormBridge.size(); index++) {
            afterFormBridge.add(getSquare(beforeFormBridge.get(index)));
        }
        return afterFormBridge;
    }

    private Square getSquare(String signature) {
        if (signature.equals(Constant.UP_SIGNATURE)) {
            return Square.UP;
        }
        if (signature.equals(Constant.DOWN_SIGNATURE)) {
            return Square.DOWN;
        }
        throw new IllegalArgumentException(ErrorMessage.NOT_MATCH_SIGNATURE.getMessage());
    }
}
