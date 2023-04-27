package vestigo.lib.services.implementations;

import org.springframework.stereotype.Service;
import vestigo.lib.services.apstractions.LetterCounterService;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReaderProvider;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderProviderException;
import vestigo.lib.services.validation.abstractions.FormatTypesValidationService;


@Service
public class LetterCounterServiceImpl implements LetterCounterService {

    private final FormatTypesValidationService _formatTypesValidationService;
    private final FormatReaderProvider _formatReaderProvider;

    public LetterCounterServiceImpl(FormatTypesValidationService formatTypesValidationService,
                                    FormatReaderProvider formatReaderProvider) {
        _formatTypesValidationService = formatTypesValidationService;
        _formatReaderProvider = formatReaderProvider;
    }

    public LetterCounterReadDto count(String text) throws FormatReaderProviderException, FormatReaderException {
        return _formatReaderProvider.provideFormatReader(
                        _formatTypesValidationService.validateAndResolveFormatType(text))
                .readFormat(text);
    }
}
