package xxl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Range implements Serializable {
    private int[] _rowAddresses = new int[2];      // [first row, last row]
    private int[] _columnAddresses = new int[2];   // [first column, last column]


    public Range(String rangeSpecification) {
        String[] entry = rangeSpecification.split(":");

        String[] firstCellAddress = entry[0].split(";");
        _rowAddresses[1] = _rowAddresses[0] = Integer.parseInt(firstCellAddress[0]);
        _columnAddresses[1] = _columnAddresses[0] = Integer.parseInt(firstCellAddress[1]);

        if (entry.length > 1) {
            String[] lastCellAddress = entry[1].split(";");
            _rowAddresses[1] = Integer.parseInt(lastCellAddress[0]);
            _columnAddresses[1] = Integer.parseInt(lastCellAddress[1]);
        }

        Arrays.sort(_rowAddresses);
        Arrays.sort(_columnAddresses);
    }

    public List<int[]> getRange() {
        List<int[]> range = new LinkedList<>();

        if (_rowAddresses[0] == _rowAddresses[1]) {
            for (int i = _columnAddresses[0]; i <= _columnAddresses[1]; i++) {
                int[] address = {_rowAddresses[0], i};
                range.add(address);
            }
        } else if (_columnAddresses[0] == _columnAddresses[1]) {
            for (int i = _rowAddresses[0]; i <= _rowAddresses[1]; i++) {
                int[] address = {_columnAddresses[0], i};
                range.add(address);
            }
        }

        return range;
    }

    public boolean isSingle() {
        return (_rowAddresses[0] == _rowAddresses[1] && _columnAddresses[0] == _columnAddresses[1]);
    }
}
