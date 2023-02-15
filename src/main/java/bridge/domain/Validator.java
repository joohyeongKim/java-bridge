package bridge.domain;

import static bridge.ui.MessageType.INVALID_COMMAND;
import static bridge.ui.MessageType.INVALID_FORMAT;
import static bridge.ui.MessageType.INVALID_MOVING;
import static bridge.ui.MessageType.INVALID_RANGE;

import bridge.interfaces.Validation;
import java.util.List;

public class Validator implements Validation {

    private static final int MINIMUM_SIZE = 3;
    private static final int MAXIMUM_SIZE = 20;



    @Override
    public void validateBridgeSize(String bridgeSize) {
        int size = toInteger(bridgeSize);
        validateRange(size);

    }

    private int toInteger(String bridgeSize) {
        try {
            return Integer.valueOf(bridgeSize);
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT.messaging());
        }
    }

    public void validateRange(int size) {
        if (size < MINIMUM_SIZE || size > MAXIMUM_SIZE) {
            throw new IllegalArgumentException(INVALID_RANGE.messaging());
        }
    }

    public void validateMove(String move){
        if (!List.of("U", "D").contains(move)) {
            throw new IllegalArgumentException(INVALID_MOVING.messaging());
        }
    }

    public void validateCommand(String command) {
        if (!List.of("R", "Q").contains(command)) {
            throw new IllegalArgumentException(INVALID_COMMAND.messaging());
        }
    }
}
