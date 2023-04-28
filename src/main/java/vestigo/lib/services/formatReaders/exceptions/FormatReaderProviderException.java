package vestigo.lib.services.formatReaders.exceptions;
/**
 * Thrown when could not resolve {@link vestigo.lib.services.formatReaders.abstractions.FormatReader} in
 * {@link vestigo.lib.services.formatReaders.abstractions.FormatReaderProvider}.
 */
public class FormatReaderProviderException extends Exception{
    public FormatReaderProviderException(String message) {
        super(message);
    }
}
