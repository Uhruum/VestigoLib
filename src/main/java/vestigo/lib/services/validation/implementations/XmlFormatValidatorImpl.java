package vestigo.lib.services.validation.implementations;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import vestigo.lib.services.validation.abstractions.FormatValidator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class XmlFormatValidatorImpl implements FormatValidator {

    public boolean isValid(String value) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dBuilder.parse(new ByteArrayInputStream(value.getBytes()));
            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            return false;
        }
    }
}
