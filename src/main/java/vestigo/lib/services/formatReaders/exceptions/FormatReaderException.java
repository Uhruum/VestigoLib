package vestigo.lib.services.formatReaders.exceptions;

public class FormatReaderException extends Exception {

    public FormatReaderException(String message, Throwable  e) {
            super(message,e);
    }

    public FormatReaderException(String message) {
        super(message);
    }
}
