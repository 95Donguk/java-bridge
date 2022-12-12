package bridge.validator;

import static bridge.utils.message.ErrorMessage.BLANK_VALUE;
import static bridge.utils.message.ErrorMessage.INVALID_BRIDGE_SIZE;
import static bridge.utils.message.ErrorMessage.NON_NUMERIC_VALUE;

public class BridgeSizeValidator implements Validator {
    public static final int MINIMUM_BRIDGE_SIZE = 3;
    public static final int MAXIMUM_BRIDGE_SIZE = 20;

    @Override
    public void validate(String inputValue) {
        checkNonNumericValue(inputValue);
        checkBlankValue(inputValue);
        checkInvalidBridgeSize(inputValue);
    }

    private void checkNonNumericValue(String inputValue) {
        if (isNonNumericValue(inputValue)) {
            throw new IllegalArgumentException(NON_NUMERIC_VALUE.getMessage());
        }
    }

    private boolean isNonNumericValue(String inputValue) {
        return !inputValue.chars().allMatch(Character::isDigit);
    }

    private void checkBlankValue(String inputValue) {
        if (inputValue.isBlank()) {
            throw new IllegalArgumentException(BLANK_VALUE.getMessage());
        }
    }

    private void checkInvalidBridgeSize(String inputValue) {
        if (isInvalidBridgeSize(inputValue)) {
            throw new IllegalArgumentException(
                    String.format(INVALID_BRIDGE_SIZE.getMessage(), MINIMUM_BRIDGE_SIZE, MAXIMUM_BRIDGE_SIZE));
        }
    }

    private boolean isInvalidBridgeSize(String inputValue) {
        int bridgeSize = Integer.parseInt(inputValue);
        return bridgeSize < MINIMUM_BRIDGE_SIZE || bridgeSize > MAXIMUM_BRIDGE_SIZE;
    }
}
