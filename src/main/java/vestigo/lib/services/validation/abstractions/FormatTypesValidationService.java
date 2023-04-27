package vestigo.lib.services.validation.abstractions;

import vestigo.lib.services.enums.FormatTypes;

public interface FormatTypesValidationService {
    FormatTypes validateAndResolveFormatType(String value);
}
