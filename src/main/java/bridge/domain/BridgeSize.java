package bridge.domain;

public final class BridgeSize {
    private static final int MIN = 3;
    private static final int MAX = 20;

    private final int size;

    public BridgeSize(int size) {
        validate(size);
        this.size = size;
    }

    private void validate(int size) {
        if (size < MIN || size > MAX) {
            throw new IllegalArgumentException();
        }
    }

    public int getSize() {
        return size;
    }
}
