package vestigo.lib.services.implementations;

import org.springframework.stereotype.Service;
import vestigo.lib.services.apstractions.LetterCounterService;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReaderProvider;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderProviderException;
import vestigo.lib.services.validation.abstractions.FormatTypesService;

/**
 * Default implementation of {@link LetterCounterService }
 */
@Service
public class LetterCounterServiceImpl implements LetterCounterService {

    private final FormatTypesService _formatTypesService;
    private final FormatReaderProvider _formatReaderProvider;

    public LetterCounterServiceImpl(FormatTypesService formatTypesService,
                                    FormatReaderProvider formatReaderProvider) {
        _formatTypesService = formatTypesService;
        _formatReaderProvider = formatReaderProvider;
    }

    public LetterCounterReadDto count(String text) throws FormatReaderProviderException, FormatReaderException {
        return _formatReaderProvider.provideFormatReader(
                        _formatTypesService.resolveFormatTypeByValidation(text))
                .readFormat(text);
    }
}
