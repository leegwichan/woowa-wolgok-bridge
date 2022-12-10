package bridge;

import bridge.constant.Square;
import bridge.dto.BridgeGameDto;
import bridge.exception.ErrorMessage;
import bridge.helper.BridgeMakerAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final List<Square> correctSquare;
    private List<Square> movedSquare;
    private int countOfTry = 1;


    public BridgeGame(BridgeMakerAdapter bridgeMaker, int size) {
        correctSquare = bridgeMaker.makeBridge(size);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(Square square) {
        if (!isContinue()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_MOVE_BRIDGE.getMessage());
        }
        movedSquare.add(square);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        movedSquare.clear();
        countOfTry++;
    }

    public boolean isContinue() {
        return isOnBridge() && (correctSquare.size() != movedSquare.size());
    }

    public boolean isOnBridge() {
        return IntStream.range(0, movedSquare.size())
                .mapToObj(index -> correctSquare.get(index) == movedSquare.get(index))
                .reduce(true, Boolean::logicalAnd);
    }

    public BridgeGameDto getBridgeGameDto() {
        return new BridgeGameDto(correctSquare, movedSquare, countOfTry, isSuccess());
    }

    private boolean isSuccess() {
        if (correctSquare.size() != movedSquare.size()) {
            return false;
        }
        return IntStream.range(0, correctSquare.size())
                .mapToObj(index -> correctSquare.get(index) == movedSquare.get(index))
                .reduce(true, Boolean::logicalAnd);
    }
}
