package vestigo.lib.services.counters.abstractions;

/**
 * Counts characters in string
 */
public interface LetterCounter {
    /**
     * Counts characters in string
     * @param value input string
     * @return {@link Long} count
     */
    long countLetters(String value);
}
