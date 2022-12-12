package bridge.controller;

import static bridge.utils.command.GameCommand.RETRY;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.Bridge;
import bridge.domain.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.function.Supplier;

public class BridgeController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeGame bridgeGame;

    private Bridge bridge;

    public BridgeController() {
        inputView = new InputView();
        outputView = new OutputView();
        bridgeGame = new BridgeGame();
    }

    public void run() {
        startGame();
        playGame();
        quitGame();
    }

    private void startGame() {
        BridgeNumberGenerator numberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(numberGenerator);

        outputView.printGameStart();
        int bridgeSize = repeat(inputView::readBridgeSize);
        bridge = new Bridge(bridgeMaker.makeBridge(bridgeSize));
    }

    private void playGame() {
        do {
            String moving = repeat(inputView::readMoving);
            bridgeGame.move(moving, bridge.findBlockByIndex(bridgeGame.getMovingCount()));
            outputView.printMap(bridgeGame);
        } while (canMove());
    }

    private boolean canMove() {
        if (bridgeGame.isMovingFail()) {
            return choiceRetryOrQuit();
        }
        return bridgeGame.canMoveMoreBlock(bridge.length());
    }

    private boolean choiceRetryOrQuit() {
        String gameCommand = repeat(inputView::readGameCommand);
        if (RETRY.equalCommand(gameCommand)) {
            bridgeGame.retry();
            return true;
        }
        return false;
    }

    private void quitGame() {
        outputView.printResult(bridgeGame);
    }

    private <T> T repeat(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return repeat(inputReader);
        }
    }
}
