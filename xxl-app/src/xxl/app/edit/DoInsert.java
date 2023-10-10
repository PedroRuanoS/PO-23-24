package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedEntryException;
// FIXME import classes

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

    DoInsert(Spreadsheet receiver) {
        super(Label.INSERT, receiver);
        addStringField("rangeSpecification", Prompt.address());
        addStringField("contentSpecification", Prompt.content());
    }

    @Override
    protected final void execute() throws CommandException {
        /*try {
            _receiver.insertContents(stringField("rangeSpecification"), stringField("contentSpecification"));
        } catch (UnrecognizedEntryException e) {
            throw new InvalidCellRangeException(e.getEntrySpecification());
        }*/
    }

}
