package bridge.domain;

import static bridge.ui.MessageType.INVALID_COMMAND;
import static bridge.ui.MessageType.INVALID_FORMAT;
import static bridge.ui.MessageType.INVALID_MOVING;
import static bridge.ui.MessageType.INVALID_RANGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("검증기 구현체")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ValidatorTest {

    private Validator inputValidator;

    @BeforeEach
    void setUp() {
        this.inputValidator = new Validator();
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 21, 999})
    void 범위를_벗어난_값_입력(int bridgeSize){
        assertThatThrownBy(() -> inputValidator.validateRange(bridgeSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_RANGE.messaging());
    }

    @ParameterizedTest
    @ValueSource(strings = {"삼","twenty", " ", ""})
    void 숫자가_아닌_값을_입력(String bridgeSize){
        assertThatThrownBy(() -> inputValidator.validateBridgeSize(bridgeSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_FORMAT.messaging());
    }

    @ParameterizedTest
    @ValueSource(strings = {"위","아래", "u", "d"," ",""," U"})
    public void U와_D가_아닌_값을_입력(String move){
        assertThatThrownBy(() -> inputValidator.validateMove(move))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_MOVING.messaging());
    }

    @ParameterizedTest
    @ValueSource(strings = {"r", "q", "Quit", "배가 너무 고파요"})
    public void 재시도_여부가_아닌_값을_입력(String command){
        assertThatThrownBy(() -> inputValidator.validateCommand(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_COMMAND.messaging());
    }

}