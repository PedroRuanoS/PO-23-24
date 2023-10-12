package xxl.app.main;

import java.io.IOException;
import java.io.FileNotFoundException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.Calculator;
import xxl.exceptions.MissingFileAssociationException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

    DoSave(Calculator receiver) {
        super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
        //addStringField("filename", Prompt.newSaveAs()); FIXME REMOVE THIS
    }

    @Override
    protected final void execute() {
        try {
            _receiver.save();
        } catch (MissingFileAssociationException e) {
            try {
                _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
            } catch (MissingFileAssociationException e1) {

            } catch (IOException e1) {

            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }       
}
