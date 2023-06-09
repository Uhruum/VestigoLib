package vestigo.lib.services.validation.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import vestigo.lib.services.validation.abstractions.FormatValidator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Implementation of {@link FormatValidator} as Yaml format validator.
 * Validates input string with {@link Yaml}.
 */
@Component
@Qualifier("YamlFormatValidatorImpl")
public class YamlFormatValidatorImpl implements FormatValidator {

    /**
     * Implementation of {@link FormatValidator} as Yaml format validator.
     * Validates input string with {@link Yaml}.
     */
    public boolean isValid(String value) {
        try {
            InputStream targetStream = new ByteArrayInputStream(value.getBytes());
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(targetStream);
            return !data.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
