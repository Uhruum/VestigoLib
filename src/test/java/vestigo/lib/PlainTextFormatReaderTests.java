package vestigo.lib;

import org.junit.jupiter.api.Test;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.implementations.PlainTextFormatReaderImpl;
import vestigo.lib.services.implementations.ConsonantLetterCounterImpl;
import vestigo.lib.services.implementations.VowelsLetterCounterImpl;
import vestigo.lib.services.implementations.VowelsProviderImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlainTextFormatReaderTests {

    private final FormatReader _plainTextFormatReader;

    public PlainTextFormatReaderTests() {
        VowelsProviderImpl vowelsProvider = new VowelsProviderImpl();
        _plainTextFormatReader = new PlainTextFormatReaderImpl(new VowelsLetterCounterImpl(vowelsProvider),
                new ConsonantLetterCounterImpl(vowelsProvider));
    }

    @Test
    void givenStringWhenPlainTextFormatReaderReadFormatInvokedThenResultIsNotNull() throws FormatReaderException {
        String xml= "Lorem ipsum dolor sit amet.";
        LetterCounterReadDto format = _plainTextFormatReader.readFormat(xml);
        assertNotNull(format);
    }

    @Test
    void givenStringStringPlainTextXmlFormatReaderReadFormatInvokedThenVowelCountConsonantCountIsCorrect() throws FormatReaderException{
        String xml= "Lorem ipsum dolor sit amet.";
        LetterCounterReadDto format = _plainTextFormatReader.readFormat(xml);
        assertEquals(9, format.vowelCount);
        assertEquals(13, format.consonantCount);
    }
}
