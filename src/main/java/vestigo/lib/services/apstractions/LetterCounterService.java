package vestigo.lib.services.apstractions;

import vestigo.lib.services.dtos.LetterCounterReadDto;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderException;
import vestigo.lib.services.formatReaders.exceptions.FormatReaderProviderException;

/**
 * <h1>LetterCounterService</h1>
 * <strong>Manages counting vowels and consonants in given string.</strong>
 * Supported string formats are : <strong>PlainText, XML, JSON, YAML</strong>.
 * <strong>When reading those formats next rules are applied:</strong>
 * <ul>
 *    <li>PlainText
 *             <ul>
 *                 <li>whole text is considered for counting both vowels and consonants</li>
 *             </ul>
 *     </li>
 *     <li>XML <ul>
 *                 <li>for counting consonants whole text is taken</li>
 *                 <li>for counting vowels only values of attributes and elements are taken in consideration</li>
 *             </ul>
 *     </li>
 *     <li>JSON <ul>
 *                  <li>for counting consonants whole text is taken</li>
 *                  <li>for counting vowels only values of properties are taken in consideration</li>
 *              </ul>
 *     </li>
 *     <li>YAML <ul>
*                   <li>for counting consonants whole text is taken</li>
 *                  <li>for counting vowels only values of properties are taken in consideration</li>
 *              </ul>
 *     </li>
 * </ul>
 */
public interface LetterCounterService {
    /**
     * Manages counting vowels and consonants in given string.
     * Supported string formats are : <strong>PlainText, XML, JSON, YAML</strong>.
     * <strong>When reading those formats next rules are applied:</strong>
     * <ul>
     *      <li>PlainText
     *              <ul>
     *                  <li>whole text is considered for counting both vowels and consonants</li>
     *               </ul>
     *    </li>
     *     <li>XML <ul>
     *                 <li>for counting consonants whole text is taken</li>
     *                 <li>for counting vowels only values of attributes and elements are taken in consideration</li>
     *             </ul>
     *     </li>
     *     <li>JSON <ul>
     *                  <li>for counting consonants whole text is taken</li>
     *                  <li>for counting vowels only values of properties are taken in consideration</li>
     *              </ul>
     *     </li>
     *     <li>YAML <ul>
     *                   <li>for counting consonants whole text is taken</li>
     *                  <li>for counting vowels only values of properties are taken in consideration</li>
     *              </ul>
     *     </li>
     * </ul>
     *
     * @param text Source for counting vowels and consonants
     * @return {@link LetterCounterReadDto}
     */
    LetterCounterReadDto count(String text) throws FormatReaderProviderException, FormatReaderException;
}
