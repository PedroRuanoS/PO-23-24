package xxl.app.main;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

/** Exception for reporting general problems opening and processing files. */
public class FileOpenFailedException extends CommandException {

	@Serial
	private static final long serialVersionUID = 202308312359L;

	public FileOpenFailedException(Exception e) {
                super(Message.problemOpeningFile(e), e);
        }

}
