package bridge;

import bridge.domain.Bridge;
import bridge.domain.GameStatus;
import bridge.util.BridgeStatus;
import bridge.util.Moving;

public class BridgeGame {
    private final Bridge bridge;
    private final GameStatus gameStatus;
    private BridgeStatus bridgeStatus;

    BridgeGame(Bridge bridge) {
        this.bridge = bridge;
        this.gameStatus = new GameStatus();
        this.bridgeStatus = BridgeStatus.CROSSING;
        gameStatus.init();
    }

    public void move(Moving moving) {
        boolean isSuccess = bridge.isMovable(gameStatus.getPosition(), moving);
        gameStatus.move(moving, bridge.isMovable(gameStatus.getPosition(), moving));
        bridgeStatus = findBridgeStatus(isSuccess);
    }

    private BridgeStatus findBridgeStatus(boolean isSuccess) {
        if (!isSuccess) {
            return BridgeStatus.FALL;
        }
        if (gameStatus.getPosition() == bridge.size()) {
            return BridgeStatus.CROSSED;
        }
        return BridgeStatus.CROSSING;
    }

    public void retry() {
        gameStatus.init();
    }

    public boolean isCrossing() {
        return bridgeStatus.equals(BridgeStatus.CROSSING);
    }

    public boolean isCrossed() {
        return bridgeStatus.equals(BridgeStatus.CROSSED);
    }

    public boolean isFall() {
        return bridgeStatus.equals(BridgeStatus.FALL);
    }

    public String getMapToString() {
        return gameStatus.getMapToString();
    }

    public int getAttempt() {
        return gameStatus.getAttempt();
    }
}
