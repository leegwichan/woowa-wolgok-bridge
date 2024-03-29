package bridge.view.output;

import bridge.constant.Square;
import bridge.dto.BridgeGameDto;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String GAME_START = "다리 건너기 게임을 시작합니다.";
    private static final String GAME_RESULT = "최종 게임 결과";
    private static final String BLANK = "";
    private static final String GAME_SUCCESS = "게임 성공 여부: ";
    private static final String COUNT_OF_TRY = "총 시도한 횟수: ";
    private static final String SUCCESS_LETTER = "성공";
    private static final String FAIL_LETTER = "실패";

    private static final String BRIDGE_PREFIX = "[ ";
    private static final String BRIDGE_SUFFIX = " ]";
    private static final String BRIDGE_DELIMITER = " | ";

    private static final String MOVE_SUCCESS = "O";
    private static final String MOVE_FAIL = "X";
    private static final String NOTHING = " ";

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(BridgeGameDto dto) {
        printRoad(dto, Square.UP);
        printRoad(dto, Square.DOWN);
    }

    private void printRoad(BridgeGameDto dto, Square road) {
        String result = IntStream.range(0, dto.getMovedSquare().size())
                .mapToObj(index -> getMark(dto.getCorrectSquare().get(index), dto.getMovedSquare().get(index), road))
                .collect(Collectors.joining(BRIDGE_DELIMITER, BRIDGE_PREFIX, BRIDGE_SUFFIX));

        print(result);
    }

    private String getMark(Square correct, Square moved, Square road) {
        if (moved != road) {
            return NOTHING;
        }
        if (moved == correct) {
            return MOVE_SUCCESS;
        }
        return MOVE_FAIL;
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(BridgeGameDto dto) {
        print(GAME_RESULT);
        printMap(dto);
        print(BLANK);
        printIsSuccess(dto.getSuccess());
        printCountOfTry(dto.getCountOfTry());
    }

    private void printIsSuccess(boolean isSuccess) {
        if (isSuccess) {
            print(GAME_SUCCESS + SUCCESS_LETTER);
            return;
        }
        print(GAME_SUCCESS + FAIL_LETTER);
    }

    private void printCountOfTry(int countOfTry) {
        print(COUNT_OF_TRY + countOfTry);
    }

    public void printStartGame() {
        print(GAME_START);
    }

    public void printErrorMessage(Exception exception) {
        print(exception.getMessage());
    }

    private void print(String message) {
        System.out.println(message);
    }
}
