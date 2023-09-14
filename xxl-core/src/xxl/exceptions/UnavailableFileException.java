package xxl.exceptions;

import java.io.Serial;

/**
 * 
 */
public class UnavailableFileException extends Exception {

	@Serial
	private static final long serialVersionUID = 202308312359L;

	/** The requested filename. */
	String _filename;

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
