package xxl.app.search;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.Spreadsheet;
import xxl.visitor.RenderContent;
import xxl.visitor.RenderedContentVisitor;
// FIXME import classes

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

    DoShowValues(Spreadsheet receiver) {
        super(Label.SEARCH_VALUES, receiver);
    }

    @Override
    protected final void execute() {
        RenderedContentVisitor renderer = new RenderContent();
        _receiver.searchValue(Form.requestString(Prompt.searchValue()), renderer);
    }

}
