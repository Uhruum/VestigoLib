package vestigo.lib.services.formatReaders.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vestigo.lib.services.enums.FormatTypes;
import vestigo.lib.services.formatReaders.abstractions.FormatReader;
import vestigo.lib.services.formatReaders.abstractions.FormatReaderProvider;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderProviderException;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of {@link FormatReaderProvider}
 */
@Component
public class FormatReaderProviderImpl implements FormatReaderProvider {

    private final Map<FormatTypes, FormatReader> _map;

    public FormatReaderProviderImpl(@Qualifier("PlainTextFormatReaderImpl") FormatReader plainTextFormatReader,
                                    @Qualifier("XmlFormatReaderImpl") FormatReader xmlFormatReader,
                                    @Qualifier("JsonFormatReaderImpl") FormatReader jsonFormatReader,
                                    @Qualifier("YamlFormatReaderImpl") FormatReader yamlFormatReader){
        Map<FormatTypes, FormatReader> map = new HashMap<>();
        map.put(FormatTypes.PLAIN_TEXT, plainTextFormatReader);
        map.put(FormatTypes.XML, xmlFormatReader);
        map.put(FormatTypes.JSON, jsonFormatReader);
        map.put(FormatTypes.YAML, yamlFormatReader);
        _map = map;
    }


    public FormatReader provideFormatReader(FormatTypes formatType) throws FormatReaderProviderException {
        if(_map.containsKey(formatType))
            return _map.get(formatType);

        throw new FormatReaderProviderException("Could not provide requested FormatReader, mapping dose not exists!");
    }

}
