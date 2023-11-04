package xxl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User>, Serializable {

    private String _name;
    private List<Spreadsheet> _spreadsheets = new ArrayList<>();

    public User(String name) { _name = name; }

    public String getName() { return _name; }

    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.add(spreadsheet);
    }

    @Override
    public int compareTo(User user) {
        return _name.compareTo(user.getName());
    }
}
