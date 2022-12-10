package bridge.domain;

import java.util.Objects;

public class Floor {
    private final String direction;

    private Floor(String direction) {
        this.direction = direction;
    }

    public static Floor from(String direction) {
        return new Floor(direction);
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Floor)) {
            return false;
        }
        if (((Floor) obj).direction.equals(this.direction)) {
            return true;
        }
        return false;
    }
}