package bridge.domain;

import static bridge.domain.Round.ROUND_MAX;
import static bridge.domain.Round.ROUND_MIN;
import static bridge.ui.MessageType.INVALID_ROUND_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("ROUND 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RoundTest {

    @ParameterizedTest
    @ValueSource(ints = {ROUND_MIN - 1, ROUND_MAX + 1, 9999})
    void 생성자는_범위_밖의_값을_입력하면_예외처리(int number) {
        assertThatThrownBy(() -> new Round(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ROUND_RANGE.messaging());
    }

    @Test
    void nextRound_메서드를_사용하여_허용된_범위를_넘어가는_경우_예외처리() {
        //given
        Round round = new Round(ROUND_MAX);

        //then
        assertThatThrownBy(round::nextRound)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ROUND_RANGE.messaging());
    }

    @Test
    public void nextRound는_라운드_값을_1_증가시켜라(){
        //given
        Round round = new Round(1);

        //when
        round.nextRound();

        //then
        assertThat(round).isEqualTo(new Round(2));
    }

    @Test
    public void initialize는_라운드_값을_1로_초기화하라(){
        //given
        Round round = new Round(20);
        //when
        round.initialize();

        //then
        Assertions.assertEquals(round, new Round(1));
    }

    @Test
    public void naturalOrder는_Ronud를_오름차순으로_반환하라(){
        //given
        List<Round> naturalOrderRound = IntStream.rangeClosed(ROUND_MIN, ROUND_MAX)
                .mapToObj(Round::new)
                .collect(Collectors.toList());

        //then
        assertThat(Round.naturalOrder()).hasSameElementsAs(naturalOrderRound);
    }
}