package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.Bridge;
import bridge.domain.BridgeSize;
import bridge.domain.Command;
import bridge.domain.Direction;
import bridge.service.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;

public class BridgeController {

    private BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    private BridgeGame bridgeGame;

    public void run() {
        initBridge();
        while (true) {
            repeatGame();
            if (bridgeGame.canMove() || readCommand().isQuit()) {
                endGame();
                break;
            }
            bridgeGame.retry();
        }
    }

    private void initBridge() {
        BridgeSize bridgeSize = readBridgeSize();
        Bridge bridge = makeBridge(bridgeSize);
        bridgeGame = new BridgeGame(bridge);
    }

    private void repeatGame() {
        do {
            Direction direction = readMoving();
            bridgeGame.move(direction);
            OutputView.printMap(bridgeGame.getBridgeMap());
        } while (bridgeGame.canMove() && !bridgeGame.isFinish());
    }

    private BridgeSize readBridgeSize() {
        try {
            return BridgeSize.from(InputView.readBridgeSize());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readBridgeSize();
        }
    }

    private Direction readMoving() {
        try {
            return Direction.from(InputView.readMoving());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readMoving();
        }
    }

    private Command readCommand() {
        try {
            return Command.from(InputView.readGameCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand();
        }
    }

    private Bridge makeBridge(BridgeSize size) {
        List<String> bridge = bridgeMaker.makeBridge(size.getSize());
        return Bridge.from(bridge);
    }

    private void endGame() {
        OutputView.printResult(bridgeGame.getBridgeMap(), bridgeGame.canMove(), bridgeGame.getTryCount());
    }
}