import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

class AzureTranslator {
    static final String AZURE_REGION = "francecentral";
    static final String TRANSLATOR_BASE_URL = "api-eur.cognitive.microsofttranslator.com";
    static final String AZURE_TRANSLATOR_KEY = Objects.requireNonNull(
            System.getenv("AZURE_TRANSLATOR_KEY"),
            "Set AZURE_TRANSLATOR_KEY environment variable"
    );

    HttpClient client = HttpClient.newHttpClient();

    // This function performs a POST request.
    String translate(String input, String targetLang) {

        URI url = buildUrl(targetLang);
        HttpRequest.BodyPublisher body = buildRequestBody(input);
        HttpRequest request = buildRequest(body, url);

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return formatResponse(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static URI buildUrl(String targetLang) {
        try {
            return new URIBuilder()
                    .setScheme("https")
                    .setHost(TRANSLATOR_BASE_URL)
                    .setPathSegments("translate")
                    .addParameter("api-version", "3.0")
                    .addParameter("from", "en")
                    .addParameter("to", targetLang)
                    .addParameter("to", "zh")
                    .addParameter("to", "uk")
                    .addParameter("to", "ar")
                    .build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Invalid URI", e);
        }
    }

    private static HttpRequest.BodyPublisher buildRequestBody(String text) {
        return HttpRequest.BodyPublishers.ofString("""
                [
                    {
                        "Text": "$text"
                    }
                ]""".replace("$text", text));
    }

    private static HttpRequest buildRequest(HttpRequest.BodyPublisher body, URI uri) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .POST(body)
                .header("Ocp-Apim-Subscription-Region", AZURE_REGION)
                .header("Ocp-Apim-Subscription-Key", AZURE_TRANSLATOR_KEY)
                .header("Content-Type", "application/json")
                .build();
    }

    private String formatResponse(HttpResponse<String> response) {
        String responseString = Objects.requireNonNull(response.body());
        if (Objects.requireNonNull(response.headers().map().get("Content-Type")).contains("json")) {
            return prettify(responseString);
        }
        return responseString;
    }

    static String prettify(String jsonText) {
        JsonElement json = JsonParser.parseString(jsonText);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }
}
