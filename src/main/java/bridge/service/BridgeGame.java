package bridge.service;

import bridge.domain.*;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
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
        Floor nowFloor = bridge.getNowBridge(player);
        bridgeMap.addBridgeMap(nowFloor, canMove);
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
    }

    public String getBridgeMap() {
        return bridgeMap.getBridgeMap();
    }

    public int getTryCount() {
        return player.getTryCount();
    }
}
