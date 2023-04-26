package vestigo.lib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vestigo.lib.services.validation.abstractions.FormatValidator;
import vestigo.lib.services.validation.implementations.XmlFormatValidatorImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XmlFormatValidatorTests {

    private final FormatValidator _xmlFormatValidator;

    public XmlFormatValidatorTests() {
        _xmlFormatValidator = new XmlFormatValidatorImpl();
    }

    @Test
    void givenYamlStringWhenXmlFormatValidatorIsValidInvokedThenResultIsValid(){
        String value= "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><employees><employee><id>1</id><firstName>Tom</firstName><lastName>Cruise</lastName><photo>https://jsonformatter.org/img/tom-cruise.jpg</photo></employee><employee><id>2</id><firstName>Maria</firstName><lastName>Sharapova</lastName><photo>https://jsonformatter.org/img/Maria-Sharapova.jpg</photo></employee><employee><id>3</id><firstName>Robert</firstName><lastName>Downey Jr.</lastName><photo>https://jsonformatter.org/img/Robert-Downey-Jr.jpg</photo></employee></employees>";
        assertTrue(_xmlFormatValidator.isValid(value));
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
    void givenYamlStringWhenXmlFormatValidatorIsValidInvokedThenResultIsInValid(String value){
        assertFalse(_xmlFormatValidator.isValid(value));
    }
}
