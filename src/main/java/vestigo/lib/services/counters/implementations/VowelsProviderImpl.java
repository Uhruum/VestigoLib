package vestigo.lib.services.counters.implementations;

import org.springframework.stereotype.Component;
import vestigo.lib.services.counters.abstractions.VowelsProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class VowelsProviderImpl implements VowelsProvider {
    public Set<Character> getVowels() {
        return new HashSet(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    }
}
