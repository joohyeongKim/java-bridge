package bridge.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("BridgeBuilder")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BridgeBuilderTest {

    @ParameterizedTest
    @CsvSource({"KEEP_MOVE, O", "FALL, X"})
    void 이동_결과가_참이면_O_실패면_X를_반환하라(BridgeBuilder bridgeBuilder, String actual){

        //given
        String expect = bridgeBuilder.getBridgeRow();

        //then
        Assertions.assertEquals(expect, actual);

    }

}