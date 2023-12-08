package ai.cloud.images.s3;

import java.util.Optional;

public interface ImageSource {

    Optional<byte[]> getObjectBytes(String objectName);
}
