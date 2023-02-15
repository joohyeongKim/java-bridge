package bridge.ui;


import static bridge.ui.Serialization.CONSOLE;
import static org.junit.jupiter.api.Assertions.*;

import bridge.interfaces.Input;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("InputView 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InputViewTest {

    private InputView inputView;

    @BeforeEach
    void setUp(){
        this.inputView = new InputView();
    }


    @ParameterizedTest
    @ValueSource(strings = {"3","12","20"})
    public void 다리_길이를_입력_받는다(String size){
        //given
        InputStream console = CONSOLE.setConsole(size);
        System.setIn(console);

        //when
        List<Integer> expectList = List.of(3, 12, 20);
        int actual = inputView.readBridgeSize();

        //then
        expectList.forEach(expect -> assertEquals(expect, actual));
    }

    @ParameterizedTest
    @ValueSource(strings = {"U", "D", "U"})
    public void 이동할_칸을_입력_받는다(String move){
        //given
        CONSOLE.setConsole(move);
        //when
        List<String> expectList = List.of("U", "D", "U");
        String actual = inputView.readMoving();

        //then
        expectList.forEach(expect -> assertEquals(expect, actual));
    }

    @ParameterizedTest
    @ValueSource(strings = {"R", "Q"})
    public void 재시도_및_종료_여부를_입력_받는다(String command){
        //given
        CONSOLE.setConsole(command);

        //when
        List<String> expectList = List.of("R", "Q");
        String actual = inputView.readGameCommand();

        //then
        expectList.forEach(expect -> assertEquals(expect, actual));
    }
}