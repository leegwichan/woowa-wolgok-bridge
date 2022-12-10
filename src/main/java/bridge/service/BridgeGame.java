package bridge.service;

import bridge.domain.Bridge;
import bridge.domain.BridgeMap;
import bridge.domain.Direction;
import bridge.domain.Player;

public class BridgeGame {
    private static Bridge bridge;
    private BridgeMap bridgeMap = new BridgeMap();
    private Player player = new Player();
    private boolean canMove = true;

    public BridgeGame(Bridge bridge) {
        this.bridge = bridge;
    }

    public void move(Direction direction) {
        canMove = bridge.canMove(player, direction);
        bridgeMap.addBridgeMap(direction, canMove);
        player.moveForward();
    }

    public boolean canMove() {
        return canMove;
    }

    public boolean isFinish() {
        return bridge.isFinish(player);
    }

    public void retry() {
        bridgeMap.init();
        player.addTryCount();
        canMove = true;
    }

    public String getBridgeMap() {
        return bridgeMap.getBridgeMap();
    }

    public int getTryCount() {
        return player.getTryCount();
    }
}
