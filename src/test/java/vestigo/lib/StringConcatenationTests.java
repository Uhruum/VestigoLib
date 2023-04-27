package vestigo.lib;

import org.junit.jupiter.api.Test;
import vestigo.lib.services.formatReaders.abstractions.StringConcatenation;
import vestigo.lib.services.formatReaders.implementations.StringConcatenationImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringConcatenationTests {

    private final StringConcatenation _stringConcatenation;

    public StringConcatenationTests() {
        _stringConcatenation = new StringConcatenationImpl();
    }

    @Test
    void givenStringWhenStringConcatenationConcatenateInvokedThenResultIsNewStringEqualToTheGivenStrings(){
        String original = "original";
        String newValue = "test";
        String result1 = _stringConcatenation.concatenate("test",original);
        assertEquals(result1, original.concat(newValue));

        String value2= "test2";
        String result2 = _stringConcatenation.concatenate(value2,result1);
        assertEquals(result2, result1.concat(value2));
    }
}
