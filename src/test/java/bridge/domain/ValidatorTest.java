package bridge.domain;

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
    @ValueSource(strings = {"삼","twenty", " ", ""})
    void 숫자가_아닌_값을_입력(String bridgeSize){
        assertThatThrownBy(() -> inputValidator.validateBridgeSize(bridgeSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 형식입니다. 올바른 값을 입력해주세요");
    }

}