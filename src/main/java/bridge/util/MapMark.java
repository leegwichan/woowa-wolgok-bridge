package bridge.util;

public enum MapMark {
    PREFIX("[ "),
    SUFFIX(" ]"),
    DELIMITER(" | "),
    SUCCESS("O"),
    FAIL("X"),
    EMPTY(" ");

    private final String mark;

    MapMark(String mark) {
        this.mark = mark;
    }

    public String mark() {
        return mark;
    }

}
