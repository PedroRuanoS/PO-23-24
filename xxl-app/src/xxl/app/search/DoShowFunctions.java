package xxl.app.search;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.Spreadsheet;
import xxl.visitor.RenderContent;
import xxl.visitor.RenderedContentVisitor;
// FIXME import classes

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

    DoShowFunctions(Spreadsheet receiver) {
        super(Label.SEARCH_FUNCTIONS, receiver);
    }

    @Override
    protected final void execute() {
        RenderedContentVisitor renderer = new RenderContent();
        _receiver.searchFunction(Form.requestString(Prompt.searchFunction()), renderer);
    }

}
