package vestigo.lib.services.apstractions;

import vestigo.lib.services.dtos.LetterCounterServiceReadDto;

/**
 *
 */
public interface LetterCounterService {
    LetterCounterServiceReadDto count(String text);
}
