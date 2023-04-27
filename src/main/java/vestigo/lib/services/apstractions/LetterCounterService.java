package vestigo.lib.services.apstractions;

import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderProviderException;

/**
 *
 */
public interface LetterCounterService {
    LetterCounterReadDto count(String text) throws FormatReaderProviderException, FormatReaderException;
}
