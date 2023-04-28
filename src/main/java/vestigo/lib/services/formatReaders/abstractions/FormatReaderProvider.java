package vestigo.lib.services.formatReaders.abstractions;

import vestigo.lib.services.enums.FormatTypes;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderProviderException;

/**
 * Provides specific {@link FormatReader} by {@link FormatTypes}
 */
public interface FormatReaderProvider {
    /**
     * Provides specific {@link FormatReader} by {@link FormatTypes}
     * @param formatType
     * @return {@link FormatReader}
     */
    FormatReader provideFormatReader(FormatTypes formatType) throws FormatReaderProviderException;
}
