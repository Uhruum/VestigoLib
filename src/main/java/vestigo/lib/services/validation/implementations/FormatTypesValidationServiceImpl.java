package vestigo.lib.services.validation.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vestigo.lib.services.enums.FormatTypes;
import vestigo.lib.services.validation.abstractions.FormatValidator;
import vestigo.lib.services.validation.abstractions.FormatTypesValidationService;
@Component
public class FormatTypesValidationServiceImpl implements FormatTypesValidationService {

    private final FormatValidator _jsonFormatValidator;
    private final FormatValidator _xmlFormatValidator;
    private final FormatValidator _yamlFormatValidator;

    public FormatTypesValidationServiceImpl(@Qualifier("JsonFormatValidatorImpl") FormatValidator jsonFormatValidator,
                                            @Qualifier("XmlFormatValidatorImpl")  FormatValidator xmlFormatValidator,
                                            @Qualifier("YamlFormatValidatorImpl") FormatValidator yamlFormatValidator) {
        _jsonFormatValidator = jsonFormatValidator;
        _xmlFormatValidator = xmlFormatValidator;
        _yamlFormatValidator = yamlFormatValidator;
    }

    public FormatTypes validateAndResolveFormatType(String value) {

        if (_xmlFormatValidator.isValid(value))
            return FormatTypes.XML;

        if(_jsonFormatValidator.isValid(value))
            return FormatTypes.JSON;

        if(_yamlFormatValidator.isValid(value))
            return FormatTypes.YAML;

        return FormatTypes.PLAIN_TEXT;
    }
}
