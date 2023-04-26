package vestigo.lib.services.formatReaders.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vestigo.lib.services.enums.FormatTypes;
import vestigo.lib.services.validation.abstractions.FormatValidator;
import vestigo.lib.services.formatReaders.abstractions.FormatTypesResolver;
@Component
public class FormatTypesResolverImpl implements FormatTypesResolver {

    private final FormatValidator _jsonFormatValidator;
    private final FormatValidator _xmlFormatValidator;
    private final FormatValidator _yamlFormatValidator;

    public FormatTypesResolverImpl(@Qualifier("JsonFormatValidatorImpl") FormatValidator jsonFormatValidator,
                                   @Qualifier("XmlFormatValidatorImpl")  FormatValidator xmlFormatValidator,
                                   @Qualifier("YamlFormatValidatorImpl") FormatValidator yamlFormatValidator) {
        _jsonFormatValidator = jsonFormatValidator;
        _xmlFormatValidator = xmlFormatValidator;
        _yamlFormatValidator = yamlFormatValidator;
    }

    public FormatTypes resolveFormatType(String value) {

        if (_xmlFormatValidator.isValid(value))
            return FormatTypes.XML;

        if(_jsonFormatValidator.isValid(value))
            return FormatTypes.JSON;

        if(_yamlFormatValidator.isValid(value))
            return FormatTypes.YAML;

        return FormatTypes.PLAIN_TEXT;
    }
}
