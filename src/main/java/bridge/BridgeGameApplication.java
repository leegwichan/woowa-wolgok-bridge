package bridge;

import bridge.constant.RetryStatus;
import bridge.constant.Square;
import bridge.factory.BridgeFactory;
import bridge.view.input.InputView;
import bridge.view.output.OutputView;
import java.util.function.Supplier;

public class BridgeGameApplication {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private BridgeGame bridgeGame;

    public void run() {
        readRepeatWhenThrow(() -> initializeBridgeGame());

        do {
            movingRepeat();
        } while (!isFinish());

        outputView.printResult(bridgeGame.getBridgeGameDto());
    }

    private void movingRepeat() {
        if (bridgeGame.isContinue() == false) {
            bridgeGame.retry();
        }
        while (bridgeGame.isContinue()) {
            readRepeatWhenThrow(() -> move());
            outputView.printMap(bridgeGame.getBridgeGameDto());
        }
    }

    private void move() {
        Square square = inputView.readMoving();
        bridgeGame.move(square);
    }

    private boolean isFinish() {
        if (bridgeGame.isSuccess()) {
            return true;
        }
        RetryStatus status = readRepeatWhenThrow(() -> inputView.readGameCommand());
        return status == RetryStatus.Quit;
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

    private RetryStatus readRepeatWhenThrow(Supplier<RetryStatus> method) {
        while (true) {
            try {
                return method.get();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }
}
