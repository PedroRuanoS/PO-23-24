package xxl.exceptions;

import java.io.Serial;

/** 
 * Represents an error occurred during the serialization/desserialization
 * process of the apllication's state:
 *  - The specified file does not exist;
 *  - There is an error while processing this file using the Java 
 *    serialization mechanism.
 */
public class UnavailableFileException extends Exception {

	@Serial
	private static final long serialVersionUID = 202308312359L;

	/** The requested filename. */
	private String _filename;

	/**
	 * @param filename 
	 */
	public UnavailableFileException(String filename) {
	  super("Erro a processar ficheiro " + filename);
	  _filename = filename;
	}

	/**
	 * @return the requested filename
	 */
	public String getFilename() {
		return _filename;
	}

}
