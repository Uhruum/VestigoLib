package vestigo.lib.services.formatReaders.implementations;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vestigo.lib.services.apstractions.LetterCounter;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
@Component
@CommonsLog
public class XmlFormatReaderImpl implements FormatReader {

    private final static Logger _logger = Logger.getLogger(XmlFormatReaderImpl.class);
    private final LetterCounter _vowelsLetterCounter;
    private final LetterCounter _consonantLetterCounter;

    public XmlFormatReaderImpl(@Qualifier("VowelsLetterCounterImpl") LetterCounter vowelsLetterCounter,
                                    @Qualifier("ConsonantLetterCounterImpl") LetterCounter consonantLetterCounter) {
        _vowelsLetterCounter = vowelsLetterCounter;
        _consonantLetterCounter = consonantLetterCounter;
    }

    public LetterCounterReadDto readFormat(String value) {
        try {

        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }
}
