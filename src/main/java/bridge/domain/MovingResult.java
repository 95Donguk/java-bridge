package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class MovingResult {
    private final List<Boolean> results;

    public MovingResult() {
        results = new ArrayList<>();
    }

    public void addResult(Boolean movingResult) {
        results.add(movingResult);
    }

    public Boolean findByIndex(int index) {
        return results.get(index);
    }

    public Boolean hasFail() {
        return results.contains(false);
    }

    public void clear() {
        results.clear();
    }
}
