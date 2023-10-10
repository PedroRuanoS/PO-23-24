package xxl;

import java.io.IOException;
import java.io.FileNotFoundException;

import xxl.exceptions.*;

// FIXME import classes (Classes para ler e escrever em ficheiros e isso)

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet;

    // FIXME add more fields if needed (provavelmente _filename?)


    public void createSpreadsheet(int rows, int columns) {
        _spreadsheet = new Spreadsheet(rows, columns);
    }

    public void createUser() {

    }

    public Spreadsheet getSpreadsheet() { return _spreadsheet; }

    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        // FIXME implement serialization method
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
        // FIXME implement serialization method
    }

    /**
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        // FIXME implement serialization method
    }

    /**
     * Read text input file and create domain entities..
     *
     * @param filename name of the text input file
     * @throws ImportFileException
     */
    public void importFile(String filename) throws ImportFileException {
        if (_spreadsheet == null) _spreadsheet = new Spreadsheet();
        try {
            _spreadsheet.importFile(filename);
        } catch (IOException | UnrecognizedEntryException | NumberFormatException | IllegalEntryException e) {
            throw new ImportFileException(filename, e);
        }
    }

}
