## 구현할 기능 목록

- 다리를 생성하는 기능
  - 3이상 20이하의 수를 입력받아 다리의 길이를 정함
  - Random 값 0, 1을 통해 아래, 위 칸을 정함

- 플레이어가 다리를 건너는 기능
  - 위 (U), 아래 (D)를 정해 다리를 건너는 기능

- 다리를 계속 건널 수 있는지 확인하는 기능
- 게임을 다시 시도할지 확인하는 기능
- 결과 출력
  - 다리 출력
  - 게임 성공 여부, 총 시도 횟수 출력

## Class 별 구현 기능

### InputView
- [x] 다리의 길이 입력 #readBridgeSize()
  - 숫자를 입력하지 않은 경우, 에러 발생
- [x] 이동할 칸 입력 #readMoving()
  - U 또는 D를 입력하지 않은 경우, 에러 발생
- [x] 다시 시도 여부 입력 #readGameCommand()
  - R 또는 Q를 입력하지 않은 경우, 에러 발생

### OutputView
- [x] 다리 건너기 시작 안내 문구 출력 #printStartGame()
- [x] 에러 메세지 출력 #printErrorMessage()
- [x] 다리 현재 상황 출력 #printMap()
    ```text
    [ O |   |   ]
    [   | O | O ]
    ```
- [x] 최종 게임 결과 출력 #printResult()
  - 다리 현재 상황
  - 게임 성공 여부, 총 시도 횟수
    ```text
    최종 게임 결과
    [ O |   |   ]
    [   | O | O ]
    
    게임 성공 여부: 성공
    총 시도한 횟수: 2
    ```

### BridgeGame
- [x] 생성시, Bridge를 랜덤 값을 통해 생성함 BridgeGame()
- [x] 한칸 이동하도록 함 #move()
  - 현재 다리가 건널 수 없다면 에러를 발생시킴
- [x] 재시도를 위해 건넌 길을 초기화 하는 기능 #retry()
  - 건넌 길 초기화 및 시도 횟수 추가
- [x] 계속 건널 수 있는지 확인하는 기능 #isContinue()
  - 현재 경로가 올바른지 확인
  - 아직 건널 다리가 남아있는지 확인
- [x] 현재 상황을 전달함 #getBridgeDto()
- [x] 현재 성공 했는지 확인하는 기능 #isSuccess()

- 추가적으로 시간이 된다면 Bridge를 따로 구현할 것
  - 테스트 케이스 작성하느라 구현하지 못함

### BridgeDto
- [x] Bridge의 현재 상태를 가지고 있음
  - 시도 횟수, 이동 결과, 성공 했는지
- [x] getter를 통해 정보 제공

### BridgeMaker
- [x] 0과 1의 값을 통해 D, U 값을 반환 #makeBridge()
  - 0, 1의 값이 반환되지 않는 경우 에러 발생
  - 다리의 길이가 3 미만 20 초과인 경우, 에러를 발생함

### BridgeMakerAdapter
- [x] BridgeMaker에서 반환한 U, D값을 통해 Enum 값 반환
  - 반환 값이 U, D가 아닌 경우, 예외를 발생시킴

### BridgeGameApplication
- [x] 전반적인 로직 구현
  - [x] 초기화 과정 # initializeBridgeGame()
  - [x] 에러 발생시, 다시 실행하는 기능
  - [x] 반복하여 다리을 건너도록 하는 기능
  - [x] 재시도 여부를 묻는 기능
