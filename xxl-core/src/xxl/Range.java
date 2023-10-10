package xxl;


public class Range {
    private int _first_row, _first_column;

    private int _last_row, _last_column;


    public void processRange(String range) throws NumberFormatException {
        String[] interval = range.split(":");

        String[] first = interval[0].split(";");

        _first_row = Integer.parseInt(first[0]);
        _first_column = Integer.parseInt(first[1]);

        if (interval.length > 1) {
            String[] last = interval[1].split(";");
            _last_row = Integer.parseInt(last[0]);
            _last_column = Integer.parseInt(last[1]);
        } else {
            _last_row = _first_row;
            _last_column = _first_column;
        }
    }

    public int getFirstRow() { return _first_row; }
    public int getFirstColumn() { return _first_column; }
    public int getLastRow() { return _last_row; }
    public int getLastColumn() { return _last_column;}
}
