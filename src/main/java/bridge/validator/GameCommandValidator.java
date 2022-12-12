package bridge.validator;

import static bridge.utils.command.GameCommand.QUIT;
import static bridge.utils.command.GameCommand.RETRY;
import static bridge.utils.message.ErrorMessage.BLANK_VALUE;
import static bridge.utils.message.ErrorMessage.INVALID_VALUE;
import static bridge.utils.message.ErrorMessage.LOWERCASE_VALUE;

public class GameCommandValidator implements Validator {
    @Override
    public void validate(String inputValue) {
        checkBlankValue(inputValue);
        checkLowercaseValue(inputValue);
        checkInvalidGameCommand(inputValue);
    }

    private void checkBlankValue(String inputValue) {
        if (inputValue.isBlank()) {
            throw new IllegalArgumentException(BLANK_VALUE.getMessage());
        }
    }

    private void checkLowercaseValue(String inputValue) {
        if (isLowercaseValue(inputValue)) {
            throw new IllegalArgumentException(LOWERCASE_VALUE.getMessage());
        }
    }

    private boolean isLowercaseValue(String inputValue) {
        return RETRY.isLowerCaseCommand(inputValue) || QUIT.isLowerCaseCommand(inputValue);
    }

    private void checkInvalidGameCommand(String inputValue) {
        if (!isValidGameCommand(inputValue)) {
            throw new IllegalArgumentException(INVALID_VALUE.getMessage());
        }
    }

    private boolean isValidGameCommand(String inputValue) {
        return RETRY.equalCommand(inputValue) || QUIT.equalCommand(inputValue);
    }
}
