package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.visitor.RenderContent;
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
        try {
            RenderContent renderer = new RenderContent();
            _receiver.requestContents(stringField("rangeSpecification"), renderer);

            String output = renderer.toString().trim();
            if (!output.isEmpty())
                _display.popup(output);
        } catch (UnrecognizedEntryException e) {
            throw new InvalidCellRangeException(e.getEntrySpecification());
        }
    }

}
