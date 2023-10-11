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
        addIntegerField("rows", Prompt.lines());
        addIntegerField("columns", Prompt.columns());
    }

    @Override
    protected final void execute() throws CommandException {
        _receiver.createSpreadsheet(integerField("rows"), integerField("columns"));
    }

}

