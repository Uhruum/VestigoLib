package vestigo.lib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.implementations.StringConcatenationImpl;
import vestigo.lib.services.formatReaders.implementations.XmlFormatReaderImpl;
import vestigo.lib.services.counters.implementations.ConsonantLetterCounterImpl;
import vestigo.lib.services.counters.implementations.VowelsLetterCounterImpl;
import vestigo.lib.services.counters.implementations.VowelsProviderImpl;

import static org.junit.jupiter.api.Assertions.*;

public class XmlFormatReaderTest {

    private final FormatReader _xmlFormatReader;

    public XmlFormatReaderTest() {
        VowelsProviderImpl vowelsProvider = new VowelsProviderImpl();
        _xmlFormatReader = new XmlFormatReaderImpl(new VowelsLetterCounterImpl(vowelsProvider),
                new ConsonantLetterCounterImpl(vowelsProvider),
                new StringConcatenationImpl());
    }

    @Test
    void givenXmlWhenXmlFormatReaderReadFormatInvokedThenResultIsNotNull() throws FormatReaderException {
        String xml= "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><employees><employee><id>1</id><firstName>Tom</firstName><lastName>Cruise</lastName><photo>https://jsonformatter.org/img/tom-cruise.jpg</photo></employee><employee><id>2</id><firstName>Maria</firstName><lastName>Sharapova</lastName><photo>https://jsonformatter.org/img/Maria-Sharapova.jpg</photo></employee><employee><id>3</id><firstName>Robert</firstName><lastName>Downey Jr.</lastName><photo>https://jsonformatter.org/img/Robert-Downey-Jr.jpg</photo></employee></employees>";
        LetterCounterReadDto format = _xmlFormatReader.readFormat(xml);
        assertNotNull(format);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Director:name: Spielberg\\r\\n  Movies:\\r\\n    - Movie:\\r\\n        title: E.T.\\r\\n        year: 1975\\r\\n    - Movie:\\r\\n        title: Jaws\\r\\n        year: 1982",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "  ",
            ".",
            ",",
            " ",
            "1258247851477",
            "Director:\\r\\n  name: Spielberg\\r\\n  Movies:\\r\\n    - Movie:\\r\\n        title: E.T.\\r\\n        year: 1975\\r\\n    - Movie:\\r\\n        title: Jaws\\r\\n        year: 1982",
            "{\"employees\":{\"employee\":[{\"id\":\"1\",\"firstName\":\"Tom\",\"lastName\":\"Cruise\",\"photo\":\"https://jsonformatter.org/img/tom-cruise.jpg\"},{\"id\":\"2\",\"firstName\":\"Maria\",\"lastName\":\"Sharapova\",\"photo\":\"https://jsonformatter.org/img/Maria-Sharapova.jpg\"},{\"id\":\"3\",\"firstName\":\"Robert\",\"lastName\":\"Downey Jr.\",\"photo\":\"https://jsonformatter.org/img/Robert-Downey-Jr.jpg\"}]}}"})
    void givenInvalidXmlStringsWhenXmlFormatReaderReadFormatInvokedThenThrewFormatReaderException(String value) {
        assertThrows(FormatReaderException.class,() -> _xmlFormatReader.readFormat(value));
    }

    @Test
    void givenXmlStringStringWhenXmlFormatReaderReadFormatInvokedThenVowelCountConsonantCountIsCorrect() throws FormatReaderException{
        String xml= "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root alt=\"test\"><item ref=\"test\">cosmos</item><item><![CDATA[characters with markup]]></item></root>";
        LetterCounterReadDto format = _xmlFormatReader.readFormat(xml);
        assertEquals(10, format.vowelCount);
        assertEquals(57, format.consonantCount);
    }
}
