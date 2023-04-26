package vestigo.lib.services.formatReaders.abstractions;

import vestigo.lib.services.enums.FormatTypes;

public interface FormatTypesResolver {
    FormatTypes resolveFormatType(String value);
}
