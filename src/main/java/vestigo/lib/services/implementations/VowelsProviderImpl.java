package vestigo.lib.services.implementations;

import org.springframework.stereotype.Service;
import vestigo.lib.services.apstractions.VowelsProvider;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class VowelsProviderImpl implements VowelsProvider {
    public Set<Character> getVowels() {
        return new HashSet(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    }
}
