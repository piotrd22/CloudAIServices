import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.text.translate.UnicodeEscaper;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

class DeepL {

    static final String DEEPL_HOST = "api-free.deepl.com";
    static final String DEEPL_KEY = Objects.requireNonNull(
            System.getenv("DEEPL_KEY"),
            "Set DEEPL_KEY environment variable"
    );

    HttpClient client = HttpClient.newHttpClient();

    String translate(String input, String targetLang) {
        return translate(input, targetLang, false);
    }

    String translate(String input, String targetLang, Boolean isHtml) {
        URI url = buildUrl();
        HttpRequest.BodyPublisher body = buildRequestBody(input, targetLang, isHtml);
        HttpRequest request = buildRequest(body, url);

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return formatResponse(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static URI buildUrl() {
        try {
            return new URIBuilder()
                    .setScheme("https")
                    .setHost(DEEPL_HOST)
                    .setPathSegments("v2", "translate")
                    .build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Invalid URI", e);
        }
    }

    private HttpRequest.BodyPublisher buildRequestBody(String input, String targetLang, Boolean isHtml) {
        String textEscaped = new UnicodeEscaper().translate(input);

        String jsonTemplate;
        if (isHtml) {
            jsonTemplate = "{ \"text\": [ \"$text\" ], \"target_lang\": \"$targetLang\", \"tag_handling\": \"html\" }";
        } else {
            jsonTemplate = "{ \"text\": [ \"$text\" ], \"target_lang\": \"$targetLang\" }";
        }

        String requestBody = jsonTemplate
                .replace("$text", textEscaped)
                .replace("$targetLang", targetLang);

        return HttpRequest.BodyPublishers.ofString(requestBody);
    }

    private static HttpRequest buildRequest(HttpRequest.BodyPublisher body, URI uri) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .POST(body)
                .header("Authorization", "DeepL-Auth-Key " + DEEPL_KEY)
                .header("Content-Type", "application/json")
                .build();
    }

    private String formatResponse(HttpResponse<String> response) {
        String responseString = Objects.requireNonNull(response.body());
        if (response.headers().map().get("Content-Type").contains("json")) {
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
