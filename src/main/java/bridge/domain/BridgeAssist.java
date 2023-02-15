package bridge.domain;

import static bridge.ui.MessageType.INVALID_MOVING;

import java.util.Arrays;

public enum BridgeAssist {

    UP(1, "U"),
    DOWN(0, "D");

    private final int randomNumber;
    private final String bridgeRow;

    BridgeAssist(int randomNumber, String bridgeRow) {
        this.randomNumber = randomNumber;
        this.bridgeRow = bridgeRow;
    }

    public String buildRow(int randomNumber) {
        return Arrays.stream(values())
                .filter(number -> number.randomNumber == randomNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_MOVING.messaging()))
                .bridgeRow;
    }


}
