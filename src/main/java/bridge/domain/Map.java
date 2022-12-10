package bridge.domain;

import bridge.util.MapMark;
import bridge.util.Moving;
import java.util.StringJoiner;

public class Map {
    private final StringJoiner upperMap = new StringJoiner(MapMark.DELIMITER.mark(), MapMark.PREFIX.mark(),
            MapMark.SUFFIX.mark());
    private final StringJoiner lowerMap = new StringJoiner(MapMark.DELIMITER.mark(), MapMark.PREFIX.mark(),
            MapMark.SUFFIX.mark());


    public void addMove(Moving moving, boolean isSuccess) {
        String mark = MapMark.SUCCESS.mark();
        if (!isSuccess) {
            mark = MapMark.FAIL.mark();
        }
        addMark(mark, moving);
    }

    private void addMark(String mark, Moving moving) {
        if (moving.equals(Moving.UP)) {
            upperMap.add(mark);
            lowerMap.add(MapMark.EMPTY.mark());
            return;
        }
        if (moving.equals(Moving.DOWN)) {
            upperMap.add(MapMark.EMPTY.mark());
            lowerMap.add(mark);
        }
    }

    public String toString() {
        return upperMap + lowerMap.toString();
    }
}
