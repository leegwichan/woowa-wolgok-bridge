package bridge.service;

import bridge.domain.*;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private static Bridge bridge;
    private BridgeMap bridgeMap = new BridgeMap();
    private TryCount tryCount = new TryCount();
    private boolean canMove = true;

    public BridgeGame(Bridge bridge) {
        this.bridge = bridge;
    }

    public void move(Player player, Direction direction) {
        canMove = bridge.canMove(player, direction);
        Floor nowFloor = bridge.getNowBridge(player);
        bridgeMap.addBridgeMap(nowFloor, canMove);
    }

    public boolean canMove() {
        return canMove;
    }

    public boolean isFinish(Player player) {
        return bridge.isFinish(player);
    }

    public void retry() {
        bridgeMap.init();
        tryCount.addTryCount();
    }

    public String getBridgeMap() {
        return bridgeMap.getBridgeMap();
    }
}
