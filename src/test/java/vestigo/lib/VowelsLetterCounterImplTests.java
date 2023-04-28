package vestigo.lib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vestigo.lib.services.counters.abstractions.LetterCounter;
import vestigo.lib.services.counters.implementations.VowelsLetterCounterImpl;
import vestigo.lib.services.counters.implementations.VowelsProviderImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VowelsLetterCounterImplTests {

    private final LetterCounter _vowelsLetterCounter;

    public VowelsLetterCounterImplTests() {
        _vowelsLetterCounter = new VowelsLetterCounterImpl(new VowelsProviderImpl());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Lorem ipsum dolor sit amet.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "Dignissim cras tincidunt lobortis feugiat vivamus at augue",
            "Leo a diam sollicitudin tempor",
            "Aliquam sem fringilla ut morbi tincidunt augue interdum",
            "Id aliquet risus feugiat in ante metus dictum.",
            "Aliquet eget sit amet tellus cras adipiscing."})
    void givenSourceStringsWhenVowelsLetterCounterCountLettersInvokedThenResultIsTrue(String value){
        assertTrue(_vowelsLetterCounter.countLetters(value)>2);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "!#$%.",
            "*?=)(/&%$HJKLMNZV",
            "bbbsjjjjkhj",
            "sdfghjkl",
            "?KLMNBVklmnbv----,mnmn",
            "-:;MNbnmb",
            ".",
            ",",
            " ",
            "1258247851477"})
    void givenSourceStringsWhenVowelsLetterCounterCountLettersInvokedThenResultIsEqualToZero(String value){
        assertEquals(_vowelsLetterCounter.countLetters(value),0);
    }

    @Test
    void givenStringWhenVowelsLetterCounterCountLettersInvokedThenResultIsExpected(){
        assertEquals(_vowelsLetterCounter.countLetters("Aliquet eget sit amet tellus cras adipiscing."),16);
    }
}
