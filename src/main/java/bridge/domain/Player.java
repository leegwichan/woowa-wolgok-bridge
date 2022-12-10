package bridge.domain;

public class Player {
    private int position;

    public void moveForward() {
        position++;
    }

    public int getPosition() {
        return position;
    }
}