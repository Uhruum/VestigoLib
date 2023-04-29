package vestigo.lib.services.counters.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vestigo.lib.services.counters.abstractions.AbstractLetterCounter;
import vestigo.lib.services.counters.abstractions.LetterCounter;
import vestigo.lib.services.counters.abstractions.VowelsProvider;

import java.util.Set;

/**
 * Implementation of {@link LetterCounter} as Consonant counter.
 * Counts consonants in input string.
 */
@Component
@Qualifier("ConsonantLetterCounterImpl")
public class ConsonantLetterCounterImpl extends AbstractLetterCounter implements LetterCounter {

    private final VowelsProvider _vowelsProvider;

    public ConsonantLetterCounterImpl(VowelsProvider vowelsProvider) {
        _vowelsProvider = vowelsProvider;
    }

    @Override
    protected boolean getMapKey() {
        return false;
    }

    @Override
    protected Set<Character> getVowels() {
        return _vowelsProvider.getVowels();
    }
}
