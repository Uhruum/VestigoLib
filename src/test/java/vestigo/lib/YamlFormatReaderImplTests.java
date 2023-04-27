package vestigo.lib;

import org.junit.jupiter.api.Test;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.implementations.StringConcatenationImpl;
import vestigo.lib.services.formatReaders.implementations.YamlFormatReaderImpl;
import vestigo.lib.services.implementations.ConsonantLetterCounterImpl;
import vestigo.lib.services.implementations.VowelsLetterCounterImpl;
import vestigo.lib.services.implementations.VowelsProviderImpl;

import static org.junit.jupiter.api.Assertions.*;

public class YamlFormatReaderImplTests {
    private final FormatReader _yamlFormatReader;

    public YamlFormatReaderImplTests() {
        VowelsProviderImpl vowelsProvider = new VowelsProviderImpl();
        _yamlFormatReader = new YamlFormatReaderImpl(new VowelsLetterCounterImpl(vowelsProvider),
                new ConsonantLetterCounterImpl(vowelsProvider),
                new StringConcatenationImpl());
    }

    @Test
    void givenYamlWhenYamlFormatReaderReadFormatInvokedThenResultIsNotNull() throws FormatReaderException {
        String yaml= "Director:\r\n  name: Spielberg\r\n  Movies:\r\n    - Movie:\r\n        title: E.T.\r\n        year: 1975\r\n    - Movie:\r\n        title: Jaws\r\n        year: 1982";
        LetterCounterReadDto format = _yamlFormatReader.readFormat(yaml);
        assertNotNull(format);
    }

    @Test
    void givenEmptyStringWhenYamlFormatReaderReadFormatInvokedThenThrewFormatReaderException() {
        String yaml= "";
        assertThrows(FormatReaderException.class,() -> _yamlFormatReader.readFormat(yaml));
    }

    @Test
    void givenYamlStringStringWhenYamlFormatReaderReadFormatInvokedThenVowelCountConsonantCountIsCorrect() throws FormatReaderException{
        String yaml= "Director:\r\n  name: Spielberg\r\n  Movies:\r\n    - Movie:\r\n        title: E.T.\r\n        year: 1975\r\n    - Movie:\r\n        title: Jaws\r\n        year: 1982";
        LetterCounterReadDto format = _yamlFormatReader.readFormat(yaml);
        assertEquals(5, format.vowelCount);
        assertEquals(34, format.consonantCount);
    }
}
