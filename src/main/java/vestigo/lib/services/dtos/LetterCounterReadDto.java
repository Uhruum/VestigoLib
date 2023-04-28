package vestigo.lib.services.dtos;

import lombok.Builder;
import lombok.Data;
/**
 * Data transfer object used for returning final count of vowels and consonants.
 */
@Data
@Builder
public class LetterCounterReadDto {
    public long vowelCount;
    public long consonantCount;
}
