package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
// FIXME import classes

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

    DoShow(Spreadsheet receiver) {
        super(Label.SHOW, receiver);
        addStringField("rangeSpecification", Prompt.address());

    }

    @Override
    protected final void execute() throws CommandException {
        System.out.println("execute()@DoShow");
    }

}
