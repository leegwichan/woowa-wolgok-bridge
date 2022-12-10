package bridge.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class BridgeMap {
    private static final String MAP_PREFIX = "[ ";
    private static final String MAP_SUFFIX = " ]";
    private static final String MAP_DELIMITER = " | ";
    private static final String CORRECT_MARK = "O";
    private static final String WRONG_MARK = "X";
    private static final String BLANK = " ";
    private StringJoiner bridgeTopMap;
    private StringJoiner bridgeBottomMap;

    public BridgeMap() {
        init();
    }

    public void addBridgeMap(Floor floor, boolean canMove) {
        String mark = getMark(canMove);
        if (floor.getDirection().equals("U")) {
            addMarkMap(bridgeTopMap, bridgeBottomMap, mark);
            return;
        }
        addMarkMap(bridgeBottomMap, bridgeTopMap, mark);
    }

    private void addMarkMap(StringJoiner exists, StringJoiner none, String mark) {
        exists.add(mark);
        none.add(BLANK);
    }

    private String getMark(boolean canMove) {
        if (canMove) {
            return CORRECT_MARK;
        }
        return WRONG_MARK;
    }

    public String getBridgeMap() {
        return bridgeTopMap.toString() + " \n" + bridgeBottomMap.toString();
    }

    public void init() {
        bridgeTopMap = new StringJoiner(MAP_DELIMITER, MAP_PREFIX, MAP_SUFFIX);
        bridgeBottomMap = new StringJoiner(MAP_DELIMITER, MAP_PREFIX, MAP_SUFFIX);
    }
}