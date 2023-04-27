package vestigo.lib.services.formatReaders.abstractions;

import vestigo.lib.services.enums.FormatTypes;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderProviderException;

public interface FormatReaderProvider {
    FormatReader provideFormatReader(FormatTypes formatType) throws FormatReaderProviderException;
}
