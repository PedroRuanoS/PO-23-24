package xxl;


import xxl.exceptions.InvalidEntryException;

public class Range {
    private int _first_cell;
    private int _last_cell;

    public Range(String range) {
        processRange(range);
    }

    private void processRange(String range) {
        String[] interval = range.split(":");

        String[] first = interval[0].split(";");
        String[] last;
        if (interval.length > 1) last = interval[1].split(";");




    }
}
