package vestigo.lib.services.formatReaders.exceptions;
/**
 * Thrown when any error occurred in {@link vestigo.lib.services.formatReaders.abstractions.FormatReader}.
 */
public class FormatReaderException extends Exception {

    public FormatReaderException(String message, Throwable  e) {
            super(message,e);
    }

    public FormatReaderException(String message) {
        super(message);
    }
}
