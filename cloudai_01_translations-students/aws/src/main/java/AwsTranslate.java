import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.amazonaws.services.translate.model.*;

class AwsTranslate {

    String translate(String input, String sourceLang, String targetLang) {
        AWSCredentialsProvider awsCredentials = DefaultAWSCredentialsProviderChain.getInstance();

        AmazonTranslate client = AmazonTranslateClient.builder()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials.getCredentials()))
                .withRegion(Regions.EU_WEST_3)
                .build();


        TranslateTextRequest request = new TranslateTextRequest()
                .withText(input)
                .withSourceLanguageCode(sourceLang)
                .withTargetLanguageCode(targetLang);

        TranslateTextResult result = client.translateText(request);

        return result.getTranslatedText();
    }
}
