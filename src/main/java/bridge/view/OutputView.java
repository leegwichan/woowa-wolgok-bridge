package bridge.view;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public void printMap(String map) {
        System.out.println(map);
    }

    public void printResult(String map, int attempt, boolean isSuccess) {
        System.out.println("최종 게임 결과");
        printMap(map);
        printSuccessOrFail(isSuccess);
        printAttempt(attempt);
    }

    private void printSuccessOrFail(boolean isSuccess) {
        String format = "게임 성공 여부: %s\n" ;
        String result = "성공" ;
        if (!isSuccess) {
            result = "실패" ;
        }
        System.out.printf(format, result);
    }

    private void printAttempt(int attempt) {
        String format = "총 시도한 횟수: %d\n" ;
        System.out.printf(format, attempt);
    }
}
