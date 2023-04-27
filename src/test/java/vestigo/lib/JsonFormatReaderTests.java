package vestigo.lib;

import org.junit.jupiter.api.Test;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.implementations.JsonFormatReaderImpl;
import vestigo.lib.services.formatReaders.implementations.StringConcatenationImpl;
import vestigo.lib.services.implementations.ConsonantLetterCounterImpl;
import vestigo.lib.services.implementations.VowelsLetterCounterImpl;
import vestigo.lib.services.implementations.VowelsProviderImpl;

import static org.junit.jupiter.api.Assertions.*;

public class JsonFormatReaderTests {

    private final FormatReader _jsonFormatReader;

    public JsonFormatReaderTests() {
        VowelsProviderImpl vowelsProvider = new VowelsProviderImpl();
        _jsonFormatReader = new JsonFormatReaderImpl(new VowelsLetterCounterImpl(vowelsProvider),
                new ConsonantLetterCounterImpl(vowelsProvider),
                new StringConcatenationImpl());
    }

    @Test
    void givenJsonWhenJsonFormatReaderReadFormatInvokedThenResultIsNotNull() throws FormatReaderException {
        String json= "{\"employees\": {\"employee\": [{\"id\": \"1\",\"firstName\": \"Tom\",\"lastName\": \"Cruise\" }]}}";
        LetterCounterReadDto format = _jsonFormatReader.readFormat(json);
        assertNotNull(format);
    }

    @Test
    void givenEmptyStringWhenJsonFormatReaderReadFormatInvokedThenThrewFormatReaderException() {
        String json= "";
        assertThrows(FormatReaderException.class,() -> _jsonFormatReader.readFormat(json));
    }

    @Test
    void givenJsonStringStringWhenJsonFormatReaderReadFormatInvokedThenVowelCountConsonantCountIsCorrect() throws FormatReaderException{
        String json= "{\"employees\": {\"employee\": [{\"id\": \"1\",\"firstName\": \"Tom\",\"lastName\": \"Cruise\" }]}}";
        LetterCounterReadDto format = _jsonFormatReader.readFormat(json);
        assertEquals(4, format.vowelCount);
        assertEquals(26, format.consonantCount);
    }

}
