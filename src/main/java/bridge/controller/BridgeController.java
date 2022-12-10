package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.*;
import bridge.dto.BridgeStateDto;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.List;

public class BridgeController {

    private BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    private TryCount tryCount = new TryCount();
    private static Bridge bridge;
    public void run() {
        BridgeSize bridgeSize = readBridgeSize();
        bridge = makeBridge(bridgeSize);
        repeatGame();
    }

    private void repeatGame() {
        boolean isContinue = true;
        Player player = new Player();
        while (!isContinue) {
            Direction direction = readMoving();
            boolean canMove = bridge.canMove(player, Floor.from(direction.getMark()));
            BridgeStateDto nowBridgeState = bridge.getNowBridgeState(player, canMove);
        }
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
}
