package bridge.domain;

public enum BridgeBuilder {

    KEEP_MOVE("O"),
    FALL("X");

    private final String bridgeRow;

    BridgeBuilder(String bridgeRow) {
        this.bridgeRow = bridgeRow;
    }

    public String getBridgeRow() {
        return bridgeRow;
    }
}
