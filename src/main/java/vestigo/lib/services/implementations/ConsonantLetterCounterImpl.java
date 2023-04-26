package vestigo.lib.services.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import vestigo.lib.services.apstractions.AbstractLetterCounter;
import vestigo.lib.services.apstractions.LetterCounter;
import vestigo.lib.services.apstractions.VowelsProvider;

import java.util.Set;
@Service
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
