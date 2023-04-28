package vestigo.lib.services.formatReaders.implementations;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import vestigo.lib.services.counters.abstractions.LetterCounter;
import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.abstractions.StringConcatenation;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
/**
 * Implementation of {@link FormatReader} as XML format reader.
 * Reads and parses string value as XML.
 * Rules applied when parsing for counting are:
 * <ul>
 *    <li>for counting consonants whole text is taken</li>
 *    <li>for counting vowels only values of attributes and elements are taken in consideration</li>
 * </ul>
 */
@Component
@Qualifier("XmlFormatReaderImpl")
public class XmlFormatReaderImpl implements FormatReader {

    private final static Logger _logger = Logger.getLogger(XmlFormatReaderImpl.class);
    private final LetterCounter _vowelsLetterCounter;
    private final LetterCounter _consonantLetterCounter;
    private final StringConcatenation _stringConcatenation;

    public XmlFormatReaderImpl(@Qualifier("VowelsLetterCounterImpl") LetterCounter vowelsLetterCounter,
                               @Qualifier("ConsonantLetterCounterImpl") LetterCounter consonantLetterCounter, StringConcatenation stringConcatenation) {
        _vowelsLetterCounter = vowelsLetterCounter;
        _consonantLetterCounter = consonantLetterCounter;
        _stringConcatenation = stringConcatenation;
    }
    /**
     * Implementation of {@link FormatReader} as XML format reader.
     * Reads and parses string value as XML.
     * Rules applied when parsing for counting are:
     * <ul>
     *    <li>for counting consonants whole text is taken</li>
     *    <li>for counting vowels only values of attributes and elements are taken in consideration</li>
     * </ul>
     * @param value Source for counting vowels and consonants
     * @return {@link LetterCounterReadDto}
     */
    public LetterCounterReadDto readFormat(String value) throws FormatReaderException {
        try {
            long consonantCount = _consonantLetterCounter.countLetters(value);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new ByteArrayInputStream(value.trim().getBytes()));

            Element rootNode = document.getDocumentElement();
            String mandatoryValues = "";

            if (rootNode.hasAttributes())
                mandatoryValues = parseAttributes(rootNode.getAttributes(), mandatoryValues);

            if (rootNode.hasChildNodes())
                mandatoryValues = parseChildNodes(rootNode.getChildNodes(), mandatoryValues);

            long vowelsCount = _vowelsLetterCounter.countLetters(mandatoryValues);

            return LetterCounterReadDto.builder()
                    .vowelCount(vowelsCount)
                    .consonantCount(consonantCount)
                    .build();

        } catch (Exception e) {
            _logger.error(e.getMessage(), e);
            throw new FormatReaderException("Error occurred when XmlFormatReaderImpl was invoked, error parsing document!", e);
        }
    }

    private String parseChildNodes(@NotNull NodeList elements, String mandatoryValues) {
        for (int temp = 0; temp < elements.getLength(); temp++) {
            Node node = elements.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                mandatoryValues = _stringConcatenation.concatenate(node.getNodeValue(), mandatoryValues);
                if (node.hasAttributes()) {
                    mandatoryValues = parseAttributes(node.getAttributes(), mandatoryValues);
                }
                if (node.hasChildNodes()) {
                    mandatoryValues = parseChildNodes(node.getChildNodes(), mandatoryValues);
                }
            }

            if(node.getNodeType() == Node.CDATA_SECTION_NODE || node.getNodeType() == Node.TEXT_NODE)
                mandatoryValues = _stringConcatenation.concatenate(node.getNodeValue(), mandatoryValues);
        }
        return mandatoryValues;
    }

    private String parseAttributes(@NotNull NamedNodeMap attributes, String mandatoryValues) {
        for (int x = 0; x < attributes.getLength(); x++) {
            mandatoryValues = _stringConcatenation.concatenate(attributes.item(x).getNodeValue(), mandatoryValues);
        }
        return mandatoryValues;
    }

}
