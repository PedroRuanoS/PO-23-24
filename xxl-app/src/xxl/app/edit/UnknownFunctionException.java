package xxl.app.edit;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

/**
 * Thrown when an unknown function is used.
 */
public class UnknownFunctionException extends CommandException {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    /** @param name  */
    public UnknownFunctionException(String name) {
        super("A função '" + name + "' é desconhecida.");
    }

}
