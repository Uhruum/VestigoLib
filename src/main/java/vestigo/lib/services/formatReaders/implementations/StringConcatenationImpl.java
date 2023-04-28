package vestigo.lib.services.formatReaders.implementations;

import org.springframework.stereotype.Component;
import vestigo.lib.services.formatReaders.abstractions.StringConcatenation;

/**
 * Default implementation of {@link StringConcatenation}
 */
@Component
public class StringConcatenationImpl implements StringConcatenation {

    public String concatenate(String value, String concatenatedValue) {
        return value == null ? concatenatedValue.concat("") : concatenatedValue.concat(value);
    }
}
