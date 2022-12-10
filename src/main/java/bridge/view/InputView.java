package bridge.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readBridgeSize() {
        System.out.println("다리의 길이를 입력해주세요.");
        try {
            return Integer.parseInt(Console.readLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public String readMoving() {
        return null;
    }

    public String readGameCommand() {
        return null;
    }
}
