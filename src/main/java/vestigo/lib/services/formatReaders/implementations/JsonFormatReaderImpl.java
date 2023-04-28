package vestigo.lib.services.formatReaders.implementations;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vestigo.lib.services.counters.abstractions.LetterCounter;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.abstractions.StringConcatenation;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/**
 * Implementation of {@link FormatReader} as JSON format reader.
 * Reads and parses string value as JSON.
 * Rules applied when parsing for counting are:
 * <ul>
 *   <li>for counting consonants whole text is taken</li>
 *   <li>for counting vowels only values of properties are taken in consideration</li>
 * </ul>
 */
@Component
@Qualifier("JsonFormatReaderImpl")
public class JsonFormatReaderImpl implements FormatReader {

    private final static Logger _logger = Logger.getLogger(JsonFormatReaderImpl.class);
    private final LetterCounter _vowelsLetterCounter;
    private final LetterCounter _consonantLetterCounter;
    private final StringConcatenation _stringConcatenation;

    public JsonFormatReaderImpl(@Qualifier("VowelsLetterCounterImpl") LetterCounter vowelsLetterCounter,
                                @Qualifier("ConsonantLetterCounterImpl") LetterCounter consonantLetterCounter,
                                StringConcatenation stringConcatenation) {
        _vowelsLetterCounter = vowelsLetterCounter;
        _consonantLetterCounter = consonantLetterCounter;
        _stringConcatenation = stringConcatenation;
    }
/**
 * Reads and parses string value as JSON.
 * Rules applied when parsing for counting are:
 * <ul>
 *   <li>for counting consonants whole text is taken</li>
 *   <li>for counting vowels only values of properties are taken in consideration</li>
 * </ul>
 * @param value Source for counting vowels and consonants
 * @return {@link LetterCounterReadDto}
 */
    public LetterCounterReadDto readFormat(String value) throws FormatReaderException {
        try {
            long consonantCount = _consonantLetterCounter.countLetters(value);
            Map<String, JsonElement> mappedJson = new Gson().fromJson(value,
                    new TypeToken<Map<String, JsonElement>>() {
                    }.getType());

            AtomicReference<String> mandatoryValues = new AtomicReference<>("");
            mandatoryValues.set(parseJsonMap(mappedJson, mandatoryValues).get());

            long vowelsCount = _vowelsLetterCounter.countLetters(mandatoryValues.get());

            return LetterCounterReadDto.builder()
                    .vowelCount(vowelsCount)
                    .consonantCount(consonantCount)
                    .build();

        } catch (Exception e) {
            _logger.error(e.getMessage(), e);
            throw new FormatReaderException("Error occurred when JsonFormatReaderImpl was invoked, error parsing document!", e);
        }
    }

    @Contract("_, _ -> param2")
    private AtomicReference<String> parseJsonArray(@NotNull JsonArray jsonArray, final AtomicReference<String> mandatoryValues) {
        jsonArray.forEach(jsonElement -> ParseJson(mandatoryValues, jsonElement));
        return mandatoryValues;
    }

    @Contract("_, _ -> param2")
    private AtomicReference<String> parseJsonMap(@NotNull Map<String, JsonElement> stringJsonElementMap, final AtomicReference<String> mandatoryValues) {
        stringJsonElementMap.forEach((x, y) -> ParseJson(mandatoryValues, y));
        return mandatoryValues;
    }

    private void ParseJson(AtomicReference<String> mandatoryValues, @NotNull JsonElement y) {
        if (y.isJsonPrimitive() && !y.isJsonNull())
            mandatoryValues.set(_stringConcatenation.concatenate(y.getAsString(), mandatoryValues.get()));

        if (y.isJsonArray() && !y.isJsonNull()) {
            mandatoryValues.set(parseJsonArray(y.getAsJsonArray(), mandatoryValues).get());
        }

        if (y.isJsonObject() && !y.isJsonNull())
            mandatoryValues.set(parseJsonMap(y.getAsJsonObject().asMap(), mandatoryValues).get());
    }
}
