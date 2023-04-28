package vestigo.lib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vestigo.lib.services.counters.abstractions.LetterCounter;
import vestigo.lib.services.counters.implementations.ConsonantLetterCounterImpl;
import vestigo.lib.services.counters.implementations.VowelsProviderImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsonantLetterCounterImplTests {
    private final LetterCounter _consonantLetterCounter;

    public ConsonantLetterCounterImplTests() {
        _consonantLetterCounter = new ConsonantLetterCounterImpl(new VowelsProviderImpl());
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
    void givenSourceStringsWhenConsonantLetterCounterCountLettersInvokedThenResultIsTrue(String value){
        assertTrue(_consonantLetterCounter.countLetters(value)>2);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "!#$%.",
            "*?=)(/&%$AEIUO",
            "aeiou(/&",
            "aaaaaiiiii",
            "?AAAOOOUUUooouuu----,",
            "-:;AAiiOOee",
            ".",
            ",",
            " ",
            "1258247851477"})
    void givenSourceStringsWhenConsonantLetterCounterCountLettersInvokedThenResultIsEqualToZero(String value){
        assertEquals(_consonantLetterCounter.countLetters(value),0);
    }

    @Test
    void givenStringWhenConsonantLetterCounterCountLettersInvokedThenResultIsExpected(){
        assertEquals(_consonantLetterCounter.countLetters("Aliquet eget sit amet tellus cras adipiscing."),22);
    }
}
