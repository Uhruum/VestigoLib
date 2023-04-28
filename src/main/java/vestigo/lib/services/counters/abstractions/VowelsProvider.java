package vestigo.lib.services.counters.abstractions;

import java.util.Set;

/**
 * Provides set of vowels characters
 */
public interface VowelsProvider {
    /**
     * Provides set of vowels characters
     * @return {@link Set} of {@link Character}
     */
    Set<Character> getVowels();
}
