package bridge.factory;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.helper.BridgeMakerAdapter;

public class BridgeFactory {

    public static BridgeGame createBridgeGame(int size) {
        return new BridgeGame(new BridgeMakerAdapter(new BridgeMaker(new BridgeRandomNumberGenerator())), size);
    }
}
