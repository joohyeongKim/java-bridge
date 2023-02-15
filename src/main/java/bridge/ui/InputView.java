package bridge.ui;

import static bridge.ui.MessageType.READ_COMMAND;
import static bridge.ui.MessageType.READ_MOVE;
import static bridge.ui.MessageType.READ_SIZE;
import static java.lang.Integer.parseInt;

import bridge.domain.Validator;
import bridge.interfaces.Input;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView implements Input {

    private Validator inputValidator = new Validator();

    /**
     * 다리의 길이를 입력받는다.
     */
    @Override
    public int readBridgeSize() {
        System.out.println(READ_SIZE.messaging());
        String size = Console.readLine();
        inputValidator.validateBridgeSize(size);
        return parseInt(size);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    @Override
    public String readMoving() {
        System.out.println(READ_MOVE.messaging());
        String move = Console.readLine();
        inputValidator.validateMove(move);
        return move;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    @Override
    public String readGameCommand() {
        System.out.println(READ_COMMAND.messaging());
        String command = Console.readLine();
        inputValidator.validateCommand(command);
        return command;
    }


}
