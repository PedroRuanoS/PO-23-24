package xxl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Range implements Serializable {
    private int[] _rowAddresses = new int[2];      // [first row, last row]
    private int[] _columnAddresses = new int[2];   // [first column, last column]

    public int[] getRowAddresses() { return _rowAddresses; }
    public int[] getColumnAddresses() { return _columnAddresses; }

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
        
        if (isHorizontal()) {
            for (int i = _columnAddresses[0]; i <= _columnAddresses[1]; i++) {
                int[] address = {_rowAddresses[0], i};
                range.add(address);
            }
        } else if (!isHorizontal()) {
            for (int i = _rowAddresses[0]; i <= _rowAddresses[1]; i++) {
                int[] address = {i, _columnAddresses[0]};
                range.add(address);
            }
        }

        return range;
    }

    public boolean isSingle() {
        return (isHorizontal() && isVertical());
    }

    public boolean isHorizontal() {
        return (_rowAddresses[0] == _rowAddresses[1]);
    }

    public boolean isVertical() {
        return (_columnAddresses[0] == _columnAddresses[1]);
    }

    public boolean sameDimension(Range other) {
        if (isHorizontal()) {
            return (other.isHorizontal() &&
                    (_columnAddresses[1] - _columnAddresses[0] ==
                            other.getColumnAddresses()[1] - other.getColumnAddresses()[0]));
        }
        return (other.isVertical() &&
                (_rowAddresses[1] - _rowAddresses[0] ==
                        other.getRowAddresses()[1] - other.getRowAddresses()[0]));
    }

    public int getFirstRow() {return _rowAddresses[0];}
    public int getFirstColumn() {return _columnAddresses[0];}
    public int getLastRow() {return _rowAddresses[1];}
    public int getLastColumn() {return _columnAddresses[1];}

}
