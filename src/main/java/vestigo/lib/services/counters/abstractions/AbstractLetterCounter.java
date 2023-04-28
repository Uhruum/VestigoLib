package vestigo.lib.services.counters.abstractions;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

 public abstract class AbstractLetterCounter implements LetterCounter {

    private static final char CHAR_A = 'a';
    private static final char CHAR_Z = 'z';

    protected abstract boolean getMapKey();

    protected abstract Set<Character> getVowels();

    public long countLetters(String value) {
        return getBooleanLongMap(value).get(getMapKey());
    }

    private Map<Boolean, Long> getBooleanLongMap(String text) {
        return text.toLowerCase(Locale.ROOT).chars()
                .mapToObj(c -> (char) c)
                .filter(ch -> (ch >= CHAR_A && ch <= CHAR_Z))
                .collect(partitioningBy(c -> getVowels().contains(c), counting()));
    }
}
