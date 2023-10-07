package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
// FIXME import classes

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

    DoCopy(Spreadsheet receiver) {
        super(Label.COPY, receiver);
        addStringField("rangeSpecification", Prompt.address());
    }

    @Override
    protected final void execute() throws CommandException {
        System.out.println("execute()@DoCopy");
    }

}
