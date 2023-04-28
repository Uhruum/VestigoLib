package vestigo.lib.services.formatReaders.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vestigo.lib.services.counters.abstractions.LetterCounter;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;

/**
 * Implementation of {@link FormatReader} as PlainText format reader.
 * Reads and parses string value as PlainText.
 * Rules applied when parsing for counting are:
 * <ul>
 *   <li>whole text is considered for counting both vowels and consonants</li>
 * </ul>
 */
@Component
@Qualifier("PlainTextFormatReaderImpl")
public class PlainTextFormatReaderImpl implements FormatReader {

    private final static Logger _logger = Logger.getLogger(PlainTextFormatReaderImpl.class);
    private final LetterCounter _vowelsLetterCounter;
    private final LetterCounter _consonantLetterCounter;

    public PlainTextFormatReaderImpl(@Qualifier("VowelsLetterCounterImpl") LetterCounter vowelsLetterCounter,
                                     @Qualifier("ConsonantLetterCounterImpl") LetterCounter consonantLetterCounter) {
        _vowelsLetterCounter = vowelsLetterCounter;
        _consonantLetterCounter = consonantLetterCounter;
    }
    /**
     * Implementation of {@link FormatReader} as PlainText format reader.
     * Reads and parses string value as PlainText.
     * Rules applied when parsing for counting are:
     * <ul>
     *   <li>whole text is considered for counting both vowels and consonants</li>
     * </ul>
     * @param value Source for counting vowels and consonants
     * @return {@link LetterCounterReadDto}
     */
    public LetterCounterReadDto readFormat(String value) throws FormatReaderException {
        try {
            long consonantCount = _consonantLetterCounter.countLetters(value);
            long vowelsCount = _vowelsLetterCounter.countLetters(value);

            return LetterCounterReadDto.builder()
                    .vowelCount(vowelsCount)
                    .consonantCount(consonantCount)
                    .build();

        } catch (Exception e) {
            _logger.error(e.getMessage(), e);
            throw new FormatReaderException("Error occurred when PlainTextFormatReaderImpl was invoked, error parsing document!", e);
        }
    }
}
