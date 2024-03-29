package bridge.view.input;

import bridge.constant.Constant;
import bridge.constant.RetryStatus;
import bridge.constant.Square;
import bridge.exception.ErrorMessage;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private static final String REQUEST_BRIDGE_SIZE = "\n다리의 길이를 입력해주세요.";
    private static final String REQUEST_MOVING_FORMAT = "\n이동할 칸을 선택해주세요. (위: %s, 아래: %s)";
    private static final String REQUEST_COMMAND_FORMAT = "\n게임을 다시 시도할지 여부를 입력해주세요. (재시도: %s, 종료: %s)";

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        print(REQUEST_BRIDGE_SIZE);
        try {
            String input = read();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NUMBER_ERROR.getMessage());
        }
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public Square readMoving() {
        print(String.format(REQUEST_MOVING_FORMAT,
                Square.UP.getFormat(), Square.DOWN.getFormat()));
        String input = read();
        return Square.getSquare(input);
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public RetryStatus readGameCommand() {
        print(String.format(REQUEST_COMMAND_FORMAT,
                RetryStatus.Retry.getFormat(), RetryStatus.Quit.getFormat()));
        String input = read();
        return RetryStatus.getRetryStatus(input);
    }

    private void print(String message) {
        System.out.println(message);
    }

    private String read() {
        return Console.readLine();
    }
}
