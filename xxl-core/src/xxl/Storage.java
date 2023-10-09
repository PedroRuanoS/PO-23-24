package xxl;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<Integer, Cell> _cells;


    public Storage() {
        _cells = new HashMap<>();
    }

    public Storage(int rows, int columns) {
        _cells = new HashMap<>(rows * columns);
    }
}
