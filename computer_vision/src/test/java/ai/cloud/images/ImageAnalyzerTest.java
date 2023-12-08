package ai.cloud.images;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import software.amazon.awssdk.regions.Region;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ImageAnalyzerTest {

    private static final Region REGION = Region.EU_WEST_1;
    private static final String BUCKET = "cloudai-assets-eu-west-1";

    private final ImageAnalyzer underTest = new ImageAnalyzer(REGION, BUCKET);

    @ParameterizedTest
    @MethodSource("prepareTestData")
    void test(String imageName, int detectedFaces, int detectedSmiling) {
        // when
        PeopleStats result = underTest.analyze(imageName);

        // then
        assertThat(result.facesDetected()).isEqualTo(detectedFaces);
        assertThat(result.smiling()).isEqualTo(detectedSmiling);
    }

    private static Stream<Arguments> prepareTestData() {
        return Stream.of(
                Arguments.of("img/ajin-ajeesh-2jHXKwZAGMw-unsplash.jpg", 1, 1),
                Arguments.of("img/luise-and-nic-WfB-32ng990-unsplash.jpg", 1, 0),
                Arguments.of("img/pexels-anna-shvets-3962285.jpg", 1, 0),
                Arguments.of("img/pexels-cottonbro-studio-4427652.jpg", 0, 0),
                Arguments.of("img/pexels-mart-production-8471789.jpg", 0, 0),
                Arguments.of("img/pexels-roman-pohorecki-16170.jpg", 0, 0),
                Arguments.of("img/raphael-wild-P8BXm4Dob9U-unsplash.jpg", 1, 0),
                Arguments.of("img/windows-97pJ_0CkVEY-unsplash.jpg", 1, 1)
        );
    }

}
