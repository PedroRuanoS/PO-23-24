package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedEntryException;
// FIXME import classes

/**
 * Cut command.
 */
class DoCut extends Command<Spreadsheet> {

    DoCut(Spreadsheet receiver) {
        super(Label.CUT, receiver);
        addStringField("rangeSpecification", Prompt.address());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.cutContents(stringField("rangeSpecification"));
        } catch (UnrecognizedEntryException e) {
            throw new InvalidCellRangeException(e.getEntrySpecification());
        }
    }

}
