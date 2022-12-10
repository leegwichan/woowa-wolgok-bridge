package bridge.domain;

import bridge.util.Moving;

public class GameStatus {
    private int position = 0;
    private Map map = new Map();
    private int attempt = 0;

    public void init() {
        position = 0;
        map = new Map();
        attempt++;
    }

    public boolean move(Moving moving, boolean isSuccess) {
        map.addMove(moving, isSuccess);
        position++;
        return isSuccess;
    }

    public int getPosition() {
        return position;
    }

    public String getMapToString() {
        return map.toString();
    }

    public int getAttempt() {
        return attempt;
    }
}
