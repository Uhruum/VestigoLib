package vestigo.lib.services.validation.implementations;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vestigo.lib.services.validation.abstractions.FormatValidator;

import java.io.IOException;

@Component
@Qualifier("JsonFormatValidatorImpl")
public class JsonFormatValidatorImpl implements FormatValidator {

    public boolean isValid(String value) {
        TypeAdapter<JsonElement> strictAdapter = new Gson().getAdapter(JsonElement.class);
        try {
            JsonElement jsonElement = strictAdapter.fromJson(value);

            if (jsonElement.isJsonPrimitive())
                return false;

            return !jsonElement.isJsonNull();
        } catch (JsonSyntaxException | IOException e) {
            return false;
        }
    }
}
