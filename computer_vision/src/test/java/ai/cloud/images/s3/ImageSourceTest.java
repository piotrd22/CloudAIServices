package ai.cloud.images.s3;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.regions.Region;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ImageSourceTest {

    private final ImageSource underTest = new S3Source(Region.EU_WEST_1, "cloudai-assets-eu-west-1");

    @Test
    void shouldReadAnObjectFromBucket() {
        // when
        Optional<byte[]> result = underTest.getObjectBytes("img/windows-97pJ_0CkVEY-unsplash.jpg");

        // then
        assertThat(result).isNotEmpty();
    }
}
