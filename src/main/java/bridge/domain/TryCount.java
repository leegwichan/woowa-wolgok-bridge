package bridge.domain;

public class TryCount {
    private int tryCount = 1;

    public void addTryCount() {
        tryCount++;
    }

    public int getTryCount() {
        return tryCount;
    }
}