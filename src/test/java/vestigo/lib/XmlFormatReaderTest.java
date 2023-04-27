package vestigo.lib;

import org.junit.jupiter.api.Test;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.implementations.StringConcatenationImpl;
import vestigo.lib.services.formatReaders.implementations.XmlFormatReaderImpl;
import vestigo.lib.services.implementations.ConsonantLetterCounterImpl;
import vestigo.lib.services.implementations.VowelsLetterCounterImpl;
import vestigo.lib.services.implementations.VowelsProviderImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
