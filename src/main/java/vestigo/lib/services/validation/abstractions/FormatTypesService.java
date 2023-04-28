package vestigo.lib.services.validation.abstractions;

import vestigo.lib.services.enums.FormatTypes;

/**
 * Manage validation of input string and resolve by validation {@link FormatTypes} of input string
 */
public interface FormatTypesService {
    /**
     * Manage validation of input string and resolve by validation {@link FormatTypes} of input string.
     * @param value input string
     * @return {@link FormatTypes}
     */
    FormatTypes resolveFormatTypeByValidation(String value);
}
