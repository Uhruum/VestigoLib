package vestigo.lib.services.formatReaders.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import vestigo.lib.services.counters.abstractions.LetterCounter;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.abstractions.StringConcatenation;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Qualifier("YamlFormatReaderImpl")
public class YamlFormatReaderImpl implements FormatReader {

    private final static Logger _logger = Logger.getLogger(YamlFormatReaderImpl.class);
    private final LetterCounter _vowelsLetterCounter;
    private final LetterCounter _consonantLetterCounter;
    private final StringConcatenation _stringConcatenation;

    public YamlFormatReaderImpl(@Qualifier("VowelsLetterCounterImpl") LetterCounter vowelsLetterCounter,
                                @Qualifier("ConsonantLetterCounterImpl") LetterCounter consonantLetterCounter,
                                StringConcatenation stringConcatenation) {
        _vowelsLetterCounter = vowelsLetterCounter;
        _consonantLetterCounter = consonantLetterCounter;
        _stringConcatenation = stringConcatenation;
    }

    public LetterCounterReadDto readFormat(String value) throws FormatReaderException {
        try {
            long consonantCount = _consonantLetterCounter.countLetters(value);
            String mandatoryValues ="";
            InputStream targetStream = new ByteArrayInputStream(value.getBytes());
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(targetStream);
            if(data.isEmpty())
                throw new FormatReaderException("Error occurred when YamlFormatReaderImpl was invoked, invalid format!");

            for (String key : data.keySet()) {
                Object obj = data.get(key);
               mandatoryValues = parseObject(obj, mandatoryValues);
            }

            long vowelsCount = _vowelsLetterCounter.countLetters(mandatoryValues);

            return LetterCounterReadDto.builder()
                    .vowelCount(vowelsCount)
                    .consonantCount(consonantCount)
                    .build();

        } catch (Exception e) {
            _logger.error(e.getMessage(), e);
            throw new FormatReaderException("Error occurred when YamlFormatReaderImpl was invoked, error parsing document!", e);
        }
    }

    private String parseObject(Object obj, String mandatoryValues){

        if(obj instanceof ArrayList )
            mandatoryValues = parseArrayList((ArrayList<Object>) obj, mandatoryValues);

        if(obj instanceof LinkedHashMap)
            mandatoryValues = parseLinkedHashMap((LinkedHashMap<String, Object>)obj, mandatoryValues);

        if(!(obj instanceof LinkedHashMap) && !(obj instanceof ArrayList) && obj != null)
            mandatoryValues = _stringConcatenation.concatenate(obj.toString(), mandatoryValues);

        return mandatoryValues;
    }

    private String parseArrayList(ArrayList<Object> arrayList, String mandatoryValues){
        for (Object obj : arrayList){
           mandatoryValues = parseObject(obj, mandatoryValues);
        }
        return mandatoryValues;
    }

    private String parseLinkedHashMap(LinkedHashMap<String, Object> map, String mandatoryValues){
        for (String key : map.keySet()){
            mandatoryValues = parseObject(map.get(key), mandatoryValues);
        }
        return mandatoryValues;
    }
}
