package xxl.app.edit;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

/**
 * Thrown when an invalid cell range is used.
 */
public class InvalidCellRangeException extends CommandException {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    /** @param range  */
    public InvalidCellRangeException(String range) {
        super("A gama '" + range + "' é inválida.");
    }

}
