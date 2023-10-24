package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.visitor.RenderContent;
import xxl.visitor.RenderedContentVisitor;
// FIXME import classes

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

    DoShowCutBuffer(Spreadsheet receiver) {
        super(Label.SHOW_CUT_BUFFER, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            RenderedContentVisitor renderer = new RenderContent();
            _receiver.requestCutBufferContent(renderer);

            String output = renderer.toString().trim();
            if (!output.isEmpty())
                _display.popup(output);
        } catch (UnrecognizedEntryException e) {
            throw new InvalidCellRangeException(e.getEntrySpecification());
        }
    }

}
