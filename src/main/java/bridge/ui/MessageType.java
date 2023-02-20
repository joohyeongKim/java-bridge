package bridge.ui;

public enum MessageType {
    INVALID_RANGE("[ERROR] 다리의 길이는 3 이상 20 이하입니다."),
    INVALID_FORMAT("[ERROR] 잘못된 형식입니다. 올바른 값을 입력해주세요."),
    INVALID_MOVING("[ERROR] 이동할 칸을 올바르게 입력해주세요 (위칸: U, 아래칸: D)"),
    INVALID_COMMAND("[ERROR] 게임을 재시도 및 종료를 원하시면 올바르게 입력해주세요. (재시도: R, 종료: Q)"),
    INVALID_ROUND_RANGE("[ERROR] 허용된 라운드 범위를 벗어났습니다."),
    READ_SIZE("\n다리의 길이를 입력해주세요."),
    READ_MOVE("\n이동할 칸을 선택해주세요. (위: U, 아래: D)"),
    READ_COMMAND("\n게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");

    public String messaging() {
        return message;
    }

    private String message;

    MessageType(String message) {
        this.message = message;
    }
}
