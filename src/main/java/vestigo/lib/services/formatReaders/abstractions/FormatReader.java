package vestigo.lib.services.formatReaders.abstractions;

import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderProviderException;

/**
 * Reads {@link  String} format with specific rules.
 * Rules are:
 * <ul>
 *    <li>PlainText
 *              <ul>
 *                  <li>whole text is considered for counting both vowels and consonants</li>
 *               </ul>
 *    </li>
 *    <li>XML <ul>
 *                <li>for counting consonants whole text is taken</li>
 *                <li>for counting vowels only values of attributes and elements are taken in consideration</li>
 *             </ul>
 *     </li>
 *     <li>JSON <ul>
 *                 <li>for counting consonants whole text is taken</li>
 *                 <li>for counting vowels only values of properties are taken in consideration</li>
 *              </ul>
 *      </li>
 *      <li>YAML <ul>
 *                 <li>for counting consonants whole text is taken</li>
 *                 <li>for counting vowels only values of properties are taken in consideration</li>
 *               </ul>
 *     </li>
 * </ul>
 */
public interface FormatReader {
    /**
     * Reads {@link  String} format with specific rules.
     * Rules are:
     * <ul>
     *    <li>PlainText
     *              <ul>
     *                  <li>whole text is considered for counting both vowels and consonants</li>
     *               </ul>
     *    </li>
     *    <li>XML <ul>
     *                <li>for counting consonants whole text is taken</li>
     *                <li>for counting vowels only values of attributes and elements are taken in consideration</li>
     *             </ul>
     *     </li>
     *     <li>JSON <ul>
     *                 <li>for counting consonants whole text is taken</li>
     *                 <li>for counting vowels only values of properties are taken in consideration</li>
     *              </ul>
     *      </li>
     *      <li>YAML <ul>
     *                 <li>for counting consonants whole text is taken</li>
     *                 <li>for counting vowels only values of properties are taken in consideration</li>
     *               </ul>
     *     </li>
     * </ul>
     * @param value Source for counting vowels and consonants
     * @return {@link LetterCounterReadDto}
     */
    LetterCounterReadDto readFormat(String value) throws FormatReaderException;
}
