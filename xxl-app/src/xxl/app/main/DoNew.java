package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Calculator;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

    DoNew(Calculator receiver) {
        super(Label.NEW, receiver);
        addIntegerField("columns", Prompt.columns());
        addIntegerField("lines", Prompt.lines());
    }

    @Override
    protected final void execute() throws CommandException {
        _receiver.createSpreadsheet(integerField("lines"), integerField("columns"));
    }

}
