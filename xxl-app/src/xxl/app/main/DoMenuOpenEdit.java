package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Calculator;

/**
 * Open menu.
 */
class DoMenuOpenEdit extends Command<Calculator> {

    DoMenuOpenEdit(Calculator receiver) {
        super(Label.MENU_CALC, receiver, r -> r.getSpreadsheet() != null);
    }

    @Override
    protected final void execute() throws CommandException {
        (new xxl.app.edit.Menu(_receiver.getSpreadsheet())).open();
    }

}
