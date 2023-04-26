package vestigo.lib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vestigo.lib.services.validation.abstractions.FormatValidator;
import vestigo.lib.services.validation.implementations.JsonFormatValidatorImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonFormatValidatorTests {

    private final FormatValidator _jsonFormatValidator;

    public JsonFormatValidatorTests() {
        _jsonFormatValidator = new JsonFormatValidatorImpl();
    }

    @Test
    void givenJsonStringWhenYamlFormatValidatorIsValidInvokedThenResultIsValid(){
        //String value=  "{\"email\": \"example@com\", \"name\": \"John\"}";
        assertTrue(_jsonFormatValidator.isValid("{\"employees\": {\"employee\": [{\"id\": \"1\",\"firstName\": \"Tom\",\"lastName\": \"Cruise\" }]}}"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Director:name: Spielberg\\r\\n  Movies:\\r\\n    - Movie:\\r\\n        title: E.T.\\r\\n        year: 1975\\r\\n    - Movie:\\r\\n        title: Jaws\\r\\n        year: 1982",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "  ",
            ".",
            ",",
            " ",
            "Director:\r\n  name: Spielberg\r\n  Movies:\r\n    - Movie:\r\n        title: E.T.\r\n        year: 1975\r\n    - Movie:\r\n        title: Jaws\r\n        year: 1982",
            "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><employees><employee><id>1</id><firstName>Tom</firstName><lastName>Cruise</lastName><photo>https://jsonformatter.org/img/tom-cruise.jpg</photo></employee><employee><id>2</id><firstName>Maria</firstName><lastName>Sharapova</lastName><photo>https://jsonformatter.org/img/Maria-Sharapova.jpg</photo></employee><employee><id>3</id><firstName>Robert</firstName><lastName>Downey Jr.</lastName><photo>https://jsonformatter.org/img/Robert-Downey-Jr.jpg</photo></employee></employees>",
    })
    void givenYamlStringWhenYamlFormatValidatorIsValidInvokedThenResultIsInValid(String value){
        assertFalse(_jsonFormatValidator.isValid(value));
    }

}
