package bridge.domain;

import java.util.Objects;

public class Floor {
    private final Direction direction;

    private Floor(String direction) {
        this.direction = Direction.from(direction);
    }

    public static Floor from(String direction) {
        return new Floor(direction);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction);
    }

    @Override
    public boolean equals(Object obj) {
        if (this.direction.equals(obj)) {
            return true;
        }
        return false;
    }
}