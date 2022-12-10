package bridge;

import bridge.factory.BridgeFactory;
import bridge.view.input.InputView;
import bridge.view.output.OutputView;

public class BridgeGameApplication {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private BridgeGame bridgeGame;

    public void run() {
        readRepeatWhenThrow(() -> initializeBridgeGame());
    }

    private void initializeBridgeGame() {
        int bridgeSize = inputView.readBridgeSize();
        bridgeGame = BridgeFactory.createBridgeGame(bridgeSize);
    }

    private void readRepeatWhenThrow(Runnable method) {
        while (true) {
            try {
                method.run();
                return;
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }
}
