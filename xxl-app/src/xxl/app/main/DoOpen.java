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
        addStringField("filename", Prompt.openFile());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.load(stringField("filename"));
        } catch (UnavailableFileException | FileNotFoundException | IOException | ClassNotFoundException e) {
            throw new FileOpenFailedException(e);
        }
    }
}
