package bridge.utils.command;

import java.util.Arrays;
import java.util.Objects;

public enum MoveCommand {
    UP(1, "U"),
    DOWN(0, "D");

    private static final String INVALID_NUMBER_MESSAGE = "유효하지 않은 숫자 값 입니다.";

    private final int number;
    private final String command;

    MoveCommand(int number, String command) {
        this.number = number;
        this.command = command;
    }

    public static String convert(int number) {
        return Arrays.stream(values())
                .filter(moveCommand -> Objects.equals(moveCommand.number, number))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_NUMBER_MESSAGE))
                .command;
    }

    public String getCommand() {
        return this.command;
    }

    public boolean isLowerCaseCommand(String inputValue) {
        return Objects.equals(command.toLowerCase(), inputValue);
    }

    public boolean equalCommand(String command) {
        return Objects.equals(this.command, command);
    }

    public boolean equalNumber(int number) {
        return Objects.equals(this.number, number);
    }
}
