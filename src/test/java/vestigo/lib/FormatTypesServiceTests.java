package vestigo.lib;

import org.junit.jupiter.api.Test;
import vestigo.lib.services.enums.FormatTypes;
import vestigo.lib.services.validation.abstractions.FormatTypesService;
import vestigo.lib.services.validation.implementations.FormatTypesServiceImpl;
import vestigo.lib.services.validation.implementations.JsonFormatValidatorImpl;
import vestigo.lib.services.validation.implementations.XmlFormatValidatorImpl;
import vestigo.lib.services.validation.implementations.YamlFormatValidatorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormatTypesServiceTests {

    private final FormatTypesService _formatTypeResolver;

    public FormatTypesServiceTests() {
        _formatTypeResolver = new FormatTypesServiceImpl(new JsonFormatValidatorImpl(),new XmlFormatValidatorImpl(), new YamlFormatValidatorImpl());
    }

    @Test
    void givenStringValueWhenFormatTypeResolverResolveFormatTypeInvokedThenResultIsTextPlain(){
        assertEquals(_formatTypeResolver.resolveFormatTypeByValidation("12358555aspkšp"), FormatTypes.PLAIN_TEXT);
    }

    @Test
    void givenJsonStringValueWhenFormatTypeResolverResolveFormatTypeInvokedThenResultIsJSON(){
        assertEquals(_formatTypeResolver.resolveFormatTypeByValidation("{\"employees\": {\"employee\": [{\"id\": \"1\",\"firstName\": \"Tom\",\"lastName\": \"Cruise\" }]}}"), FormatTypes.JSON);
    }

    @Test
    void givenYamlStringValueWhenFormatTypeResolverResolveFormatTypeInvokedThenResultIsYAML(){
        assertEquals(_formatTypeResolver.resolveFormatTypeByValidation("Director:\r\n  name: Spielberg\r\n  Movies:\r\n    - Movie:\r\n        title: E.T.\r\n        year: 1975\r\n    - Movie:\r\n        title: Jaws\r\n        year: 1982"), FormatTypes.YAML);
    }

    @Test
    void givenXmlStringValueWhenFormatTypeResolverResolveFormatTypeInvokedThenResultIsXML(){
        assertEquals(_formatTypeResolver.resolveFormatTypeByValidation("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><employees><employee><id>1</id><firstName>Tom</firstName><lastName>Cruise</lastName><photo>https://jsonformatter.org/img/tom-cruise.jpg</photo></employee><employee><id>2</id><firstName>Maria</firstName><lastName>Sharapova</lastName><photo>https://jsonformatter.org/img/Maria-Sharapova.jpg</photo></employee><employee><id>3</id><firstName>Robert</firstName><lastName>Downey Jr.</lastName><photo>https://jsonformatter.org/img/Robert-Downey-Jr.jpg</photo></employee></employees>"), FormatTypes.XML);
    }
}
