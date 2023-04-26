package vestigo.lib.services.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LetterCounterServiceReadDto {
    public long VowelCount;
    public long ConsonantCount;
}
