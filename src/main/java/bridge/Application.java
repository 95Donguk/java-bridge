package bridge;

import bridge.resource.message.FixedMessage;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        System.out.println(FixedMessage.GAME_START.getMessage() + "\n");
        inputView.readBridgeSize();
    }
}
