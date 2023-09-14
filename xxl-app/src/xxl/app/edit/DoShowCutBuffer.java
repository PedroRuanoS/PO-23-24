package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import xxl.Spreadsheet;
// FIXME import classes

/**
 * Show cut buffer command.
 */
class DoShowCutBuffer extends Command<Spreadsheet> {

    DoShowCutBuffer(Spreadsheet receiver) {
        super(Label.SHOW_CUT_BUFFER, receiver);
    }

    @Override
    protected final void execute() {
        // FIXME implement command
    }

}
