package bridge.constant;

import bridge.exception.ErrorMessage;

public enum Square {

    UP(Constant.UP_SIGNATURE),
    DOWN(Constant.DOWN_SIGNATURE);

    private String format;

    Square(String format) {
        this.format = format;
    }

    public static Square getSquare(String format) {
        for (Square square : Square.values()) {
            if (square.format.equals(format)) {
                return square;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INPUT_SQUARE_FORMAT.getMessage());
    }

    public String getFormat() {
        return format;
    }
}
