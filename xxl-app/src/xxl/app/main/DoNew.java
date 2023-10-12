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
        if (_receiver.changed() && Form.confirm(Prompt.saveBeforeExit())) {
            DoSave cmd = new DoSave(_receiver);
            cmd.execute();
        }
        _receiver.createSpreadsheet(integerField("rows"), integerField("columns"));
    }

}

