package xxl.exceptions;

public class InvalidFunctionException extends Exception {
    private String _entrySpecification;

    public InvalidFunctionException(String functionName) {
        _entrySpecification = functionName;
    }

    public String getEntrySpecification() {
        return _entrySpecification;
    }
}
