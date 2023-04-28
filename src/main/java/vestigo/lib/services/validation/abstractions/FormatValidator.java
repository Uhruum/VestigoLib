package vestigo.lib.services.validation.abstractions;

/**
 * Validates input string.
 */
public interface FormatValidator {
    /**
     * Validates input string.
     * @param value input string
     * @return {@link Boolean}
     */
    boolean isValid(String value);
}
