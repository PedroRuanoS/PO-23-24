package xxl;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import xxl.exceptions.*;

// FIXME import classes (Classes para ler e escrever em ficheiros e isso)

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet = null;

    /** The current name of the file. */
    private String _filename = "";
    private User _activeUser = new User("root");
    private List<User> _allUsers = new ArrayList<>();
    // FIX ME when creating the list, has to add user named "root" to it

    /**
     * @param rows the rows the spreadsheet will have
     * @param columns the columns the spreadsheet will have
     */
    public void createSpreadsheet(int rows, int columns) {
        _spreadsheet = new Spreadsheet(rows, columns);
        _activeUser.addSpreadsheet(_spreadsheet);
    }

    public void createUser() {}

    /**
     * @return spreadsheet
     */
    public Spreadsheet getSpreadsheet() { return _spreadsheet; }
    
    /**
     * @return filename
     */
    public String getFilename() { return _filename; }
    
    /**
     * @param filename the name of the file
     */
    public void setFilename(String filename) { _filename = filename; }

    public User getActiveUser() { return _activeUser; }

    public void setActiveUser(User user) { _activeUser = user; }
    
    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        if (_filename == null || _filename.equals("")) {
            throw new MissingFileAssociationException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
            oos.writeObject(_spreadsheet);
            _spreadsheet.changed(false);
        }
    }

    /**
     * Saves the serialized application's state into the specified file. The current network is
     * associated to this file.
     *
     * @param filename the name of the file.
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        _filename = filename;
        save();
    }

    /**
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        _filename = filename;
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            _spreadsheet = (Spreadsheet) ois.readObject();
            _spreadsheet.changed(false);
        } catch (IOException | ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
    }
    
    /**
     * @return changed?
     */
    public boolean changed() {
        if (_spreadsheet == null)
            return false; 
        return _spreadsheet.hasChanged();
    }


    /**
     * Read text input file and create domain entities.
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String filename) throws ImportFileException {
        if (_spreadsheet == null) _spreadsheet = new Spreadsheet();
        try {
            _spreadsheet.importFile(filename);
        } catch (IOException | UnrecognizedEntryException | NumberFormatException  e) {
            throw new ImportFileException(filename, e);
        }
    }

}
