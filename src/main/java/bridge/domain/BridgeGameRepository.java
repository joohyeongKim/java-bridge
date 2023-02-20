package bridge.domain;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BridgeGameRepository {

    private final Map<Round, BridgeBuilder> bridgeGameStatus = new HashMap<>();

    public BridgeGameRepository() {
    }

    // 라운드와 다리 상태를 입력받아 추가
    public void addStatus(Round round, BridgeBuilder bridgeBuilder) {
        this.bridgeGameStatus.put(round, bridgeBuilder);
    }

    public void initialize() {
        bridgeGameStatus.clear();
    }

    // 1라운드 부터의 다리를 반환
    public List<BridgeBuilder> getBridge() {
        return Round.naturalOrder().stream()
                .map(bridgeGameStatus::get)
                .filter(Objects::nonNull)
                .collect(toList());
    }
}
