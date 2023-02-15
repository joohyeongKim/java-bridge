package bridge;

import static org.assertj.core.util.Lists.newArrayList;

import bridge.interfaces.BridgeNumberGenerator;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


@DisplayName("BridgeMaker")
@DisplayNameGeneration(ReplaceUnderscores.class)
class BridgeMakerTest {

    @ParameterizedTest
    @MethodSource("argumentProvider")
    public void 다리의_길이_입력시_다리의_칸_정보인_U와_D를_포함하는_다리_반환(List<Integer> number, int size, String[] actual){
        //given
        TestBridgeNumberGenerator numberGenerator = new TestBridgeNumberGenerator(number);
        BridgeMaker bridgeMaker = new BridgeMaker(numberGenerator);
        //when
        List<String> expect = bridgeMaker.makeBridge(size);

        //then
        Assertions.assertThat(expect).containsExactly(actual);
    }

    static List<Arguments> argumentProvider() {
        return Arrays.asList(
                Arguments.of(newArrayList(1,0,1), 3, new String[]{"U", "D", "U"}),
                Arguments.of(newArrayList(0, 1, 0, 1), 4, new String[]{"D", "U", "D", "U"}),
                Arguments.of(newArrayList(1,1,1,0,0), 5, new String[]{"U", "U", "U", "D", "D"}),
                Arguments.of(newArrayList(0, 0, 0, 0, 0, 0), 6, new String[]{"D", "D", "D", "D", "D"})
        );
    }

    static class TestBridgeNumberGenerator implements BridgeNumberGenerator {

        private final List<Integer> numbers;

        TestBridgeNumberGenerator(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public int generate() {
            return numbers.remove(0);
        }
    }
}