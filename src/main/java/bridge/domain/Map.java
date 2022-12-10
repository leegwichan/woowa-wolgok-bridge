package bridge.domain;

import bridge.util.MapMark;
import bridge.util.Move;
import java.util.StringJoiner;

public class Map {
    private final StringJoiner upperMap = new StringJoiner(MapMark.DELIMITER.mark(), MapMark.PREFIX.mark(),
            MapMark.SUFFIX.mark());
    private final StringJoiner lowerMap = new StringJoiner(MapMark.DELIMITER.mark(), MapMark.PREFIX.mark(),
            MapMark.SUFFIX.mark());


    public void addMove(String move, boolean isFail) {
        String mark = MapMark.SUCCESS.mark();
        if (isFail) {
            mark = MapMark.FAIL.mark();
        }
        addMark(mark, move);
    }

    private void addMark(String mark, String move) {
        if (Move.findMove(move).equals(Move.UP)) {
            upperMap.add(mark);
            lowerMap.add(MapMark.EMPTY.mark());
            return;
        }
        if (Move.findMove(move).equals(Move.DOWN)) {
            upperMap.add(MapMark.EMPTY.mark());
            lowerMap.add(mark);
        }
    }

    public String toString() {
        return upperMap + "\n" + lowerMap;
    }
}
