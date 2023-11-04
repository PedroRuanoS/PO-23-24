package xxl.app.edit;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.visitor.RenderContent;
import xxl.visitor.RenderedContentVisitor;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

    DoShow(Spreadsheet receiver) {
        super(Label.SHOW, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            RenderedContentVisitor renderer = new RenderContent();
            _receiver.requestContents(Form.requestString(Prompt.address()), renderer);

            String output = renderer.toString().trim();
            if (!output.isEmpty())
                _display.popup(output);
        } catch (UnrecognizedEntryException e) {
            throw new InvalidCellRangeException(e.getEntrySpecification());
        }
    }

}
