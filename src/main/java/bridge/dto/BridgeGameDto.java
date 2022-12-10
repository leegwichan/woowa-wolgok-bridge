package bridge.dto;

import bridge.constant.Square;
import java.util.List;

public class BridgeGameDto {

    private final List<Square> correctSquare;
    private final List<Square> movedSquare;
    private final int countOfTry;
    private final boolean isSuccess;

    public BridgeGameDto(List<Square> correctSquare, List<Square> movedSquare,
                  int countOfTry, boolean isSuccess) {
        this.correctSquare = correctSquare;
        this.movedSquare = movedSquare;
        this.countOfTry = countOfTry;
        this.isSuccess = isSuccess;
    }

    public List<Square> getCorrectSquare() {
        return correctSquare;
    }

    public List<Square> getMovedSquare() {
        return movedSquare;
    }

    public int getCountOfTry() {
        return countOfTry;
    }

    public boolean getSuccess() {
        return isSuccess;
    }
}
