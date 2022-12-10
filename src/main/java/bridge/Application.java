package bridge;

import bridge.domain.Bridge;
import bridge.domain.BridgeSize;
import bridge.util.GameCommand;
import bridge.util.Moving;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.function.Supplier;

public class Application {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    public void run() {
        Bridge bridge = Bridge.generateBridge(getInputWhile(() -> new BridgeSize(inputView.readBridgeSize())));
        BridgeGame bridgeGame = new BridgeGame(bridge);
        playGame(bridgeGame);
        outputView.printResult(bridgeGame.getMapToString(), bridgeGame.getAttempt(), bridgeGame.isCrossed());
    }

    public void playGame(BridgeGame bridgeGame) {
        while (bridgeGame.isCrossing()) {
            bridgeGame.move(getInputWhile(() -> Moving.findMoving(inputView.readMoving())));
            outputView.printMap(bridgeGame.getMapToString());
            if (bridgeGame.isFall() && getInputWhile(() ->
                    GameCommand.findCommand(inputView.readGameCommand())).equals(GameCommand.RETRY)) {
                bridgeGame.retry();
            }
        }
    }

    private <T> T getInputWhile(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
