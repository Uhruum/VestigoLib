package vestigo.lib.services.counters.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vestigo.lib.services.counters.abstractions.AbstractLetterCounter;
import vestigo.lib.services.counters.abstractions.LetterCounter;
import vestigo.lib.services.counters.abstractions.VowelsProvider;

import java.util.Set;
/**
 * Implementation of {@link LetterCounter} as Vowels counter.
 * Count vowels in input string.
 */
@Component
@Qualifier("VowelsLetterCounterImpl")
public class VowelsLetterCounterImpl extends AbstractLetterCounter implements LetterCounter {

    private final VowelsProvider _vowelsProvider;

    public VowelsLetterCounterImpl(VowelsProvider vowelsProvider) {
        _vowelsProvider = vowelsProvider;
    }

    @Override
    protected boolean getMapKey() {
        return true;
    }

    @Override
    protected Set<Character> getVowels() {
        return _vowelsProvider.getVowels();
    }
}
