package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class MovingData {
    private final List<String> data;

    public MovingData() {
        data = new ArrayList<>();
    }

    public void addData(String moving) {
        data.add(moving);
    }

    public String findByIndex(int index) {
        return data.get(index);
    }

    public Integer getMovingCount() {
        return data.size();
    }

    public void clear() {
        data.clear();
    }
}
