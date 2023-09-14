package xxl.exceptions;

import java.io.Serial;

/**
 * Exception for unknown import file entries.
 */
public class UnrecognizedEntryException extends Exception {

	@Serial
	private static final long serialVersionUID = 202308312359L;

	/** Unrecognized entry specification. */
	private String _entrySpecification;

	/**
	 * @param entrySpecification
	 */
	public UnrecognizedEntryException(String entrySpecification) {
		_entrySpecification = entrySpecification;
	}

	/**
	 * @param entrySpecification
	 * @param cause
	 */
	public UnrecognizedEntryException(String entrySpecification, Exception cause) {
		super(cause);
		_entrySpecification = entrySpecification;
	}

	/**
	 * @return the bad entry specification.
	 */
	public String getEntrySpecification() {
		return _entrySpecification;
	}

}
