package vestigo.lib.services.apstractions;

import vestigo.lib.services.dtos.LetterCounterReadDto;

/**
 *
 */
public interface LetterCounterService {
    LetterCounterReadDto count(String text);
}
