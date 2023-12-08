package ai.cloud.images;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

class ImageAnalyzer {

    private final Region region;
    private final String bucket;

    ImageAnalyzer(Region region, String bucket) {
        this.region = region;
        this.bucket = bucket;
    }

    PeopleStats analyze(String imageName) {
        DetectFacesResponse response;
        try (RekognitionClient client = RekognitionClient.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(region)
                .build()) {

            DetectFacesRequest request = getDetectFaceRequest(imageName);

            response = client.detectFaces(request);
        }

        int facesDetected = response.faceDetails().size();
        long smiling = response.faceDetails().stream()
                .filter(
                        face -> face.smile().value() && face.smile().confidence() >= 95.0f
                )
                .count();

        return new PeopleStats(facesDetected, smiling);
    }

    private DetectFacesRequest getDetectFaceRequest(String imageName) {
        return DetectFacesRequest.builder()
                .image(
                        img -> img.s3Object(
                                s3o -> s3o.name(imageName).bucket(bucket)
                        )
                )
                .attributes(Attribute.ALL)
                .build();
    }

}
