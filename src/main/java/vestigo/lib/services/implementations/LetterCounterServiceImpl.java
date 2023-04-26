package vestigo.lib.services.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vestigo.lib.services.apstractions.LetterCounter;
import vestigo.lib.services.apstractions.LetterCounterService;
import vestigo.lib.services.dtos.LetterCounterServiceReadDto;


@Service
public class LetterCounterServiceImpl implements LetterCounterService {

    private final LetterCounter _vowelsLetterCounter;
    private final LetterCounter _consonantLetterCounter;

    public LetterCounterServiceImpl(@Qualifier("VowelsLetterCounterImpl") LetterCounter vowelsLetterCounter,
                                    @Qualifier("ConsonantLetterCounterImpl") LetterCounter consonantLetterCounter) {
        _vowelsLetterCounter = vowelsLetterCounter;
        _consonantLetterCounter = consonantLetterCounter;
    }

    public LetterCounterServiceReadDto count(String text) {
        return LetterCounterServiceReadDto.builder()
                .VowelCount( _vowelsLetterCounter.countLetters(text))
                .ConsonantCount(_consonantLetterCounter.countLetters(text))
                .build();
    }
}
