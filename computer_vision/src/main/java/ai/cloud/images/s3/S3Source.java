package ai.cloud.images.s3;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.Optional;

class S3Source implements ImageSource {

    private final Region region;
    private final String bucketName;

    S3Source(Region region, String bucketName) {
        this.region = region;
        this.bucketName = bucketName;
    }

    public Optional<byte[]> getObjectBytes(String objectName) {

        S3Client s3 = getClient();

        try {
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(objectName)
                    .bucket(bucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            return Optional.of(objectBytes.asByteArray());

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return Optional.empty();
        }
    }

    private S3Client getClient() {

        return S3Client.builder()
                .region(region)
                .build();
    }
}
