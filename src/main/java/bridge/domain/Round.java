package bridge.domain;

import static bridge.ui.MessageType.INVALID_ROUND_RANGE;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Round {

    public static final int ROUND_MIN = 1;
    public static final int ROUND_MAX = 20;
    private static final List<Round> NATURAL_ORDER = generateNaturalOrder();

    private Integer round;

    public Round(Integer round) {
        validate(round);
        this.round = round;
    }

    public void nextRound() {
        validate(++round);
    }

    public void initialize() {
        round = ROUND_MIN;
    }
    private void validate (Integer round) {
        if( round < ROUND_MIN || round > ROUND_MAX ) {
            throw new IllegalArgumentException(INVALID_ROUND_RANGE.messaging());
        }
    }

    public static List<Round> naturalOrder() {
        return NATURAL_ORDER;
    }

    public static List<Round> generateNaturalOrder() {
        return IntStream.rangeClosed(ROUND_MIN, ROUND_MAX)
                .mapToObj(Round::new)
                .collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Round round = (Round) o;
        return Objects.equals(this.round, round.round);
    }
    @Override
    public int hashCode() {
        return Objects.hash(round);
    }
}
