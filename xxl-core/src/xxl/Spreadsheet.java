package xxl;

// FIXME import classes

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import xxl.exceptions.UnrecognizedEntryException;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    // FIXME define attributes
    // FIXME define contructor(s)
    // FIXME define methods

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {
        //FIXME implement method
    }

    public void copyContents(String rangeSpecification) {}

    public void pasteContents(String rangeSpecification) {}

    public void cutContents(String rangeSpecification) {}

    public void deleteContents(String rangeSpecification) {}

    public void showContents(String rangeSpecification) {}

    public Collection<String/* temporary */> showContent (String rangeSpecification) { return new LinkedList<>(); }

    public Collection<String/* temporary */> findFunction(String functionName) { return new LinkedList<>(); }

}
