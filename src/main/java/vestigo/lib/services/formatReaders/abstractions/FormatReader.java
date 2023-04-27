package vestigo.lib.services.formatReaders.abstractions;

import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;

public interface FormatReader {
    LetterCounterReadDto readFormat(String value) throws FormatReaderException;
}
