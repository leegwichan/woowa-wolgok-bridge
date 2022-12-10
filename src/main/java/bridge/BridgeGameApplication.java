package bridge;

import bridge.constant.Square;
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

    private void movingRepeat() {
        while (bridgeGame.isContinue()) {
            readRepeatWhenThrow(() -> move());
            outputView.printMap(bridgeGame.getBridgeGameDto());
        }
    }

    private void move() {
        Square square = inputView.readMoving();
        bridgeGame.move(square);
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
