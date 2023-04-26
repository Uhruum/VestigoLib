package vestigo.lib.services.formatReaders.abstractions;

import vestigo.lib.services.dtos.LetterCounterReadDto;

public interface FormatReader {
    LetterCounterReadDto readFormat(String value);
}
