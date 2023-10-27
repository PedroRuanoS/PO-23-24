package xxl.app.main;


import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Calculator;
import xxl.exceptions.UnavailableFileException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

    DoOpen(Calculator receiver) {
        super(Label.OPEN, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            if (_receiver.changed() && Form.confirm(Prompt.saveBeforeExit())) {
                DoSave cmd = new DoSave(_receiver);
                cmd.execute();
            }
            _receiver.load(Form.requestString(Prompt.openFile()));
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }
}
