package vestigo.lib.services.validation.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import vestigo.lib.services.validation.abstractions.FormatValidator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Implementation of {@link FormatValidator} as XML format validator.
 * Validates input string with {@link DocumentBuilder}.
 */
@Component
@Qualifier("XmlFormatValidatorImpl")
public class XmlFormatValidatorImpl implements FormatValidator {

    /**
     * Implementation of {@link FormatValidator} as XML format validator.
     * Validates input string with {@link DocumentBuilder}.
     */
    public boolean isValid(String value) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.parse(new ByteArrayInputStream(value.trim().getBytes()));
            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            return false;
        }
    }
}
