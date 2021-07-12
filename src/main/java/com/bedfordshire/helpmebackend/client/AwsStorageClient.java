package com.bedfordshire.helpmebackend.client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bedfordshire.helpmebackend.exception.CustomInternalServerException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Lakitha Prabudh
 */
@Component
public class AwsStorageClient {
    private AmazonS3 s3client;
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion("ap-southeast-1").build();
    }

    public String uploadAsset(String type, String uuid, MultipartFile multipartFile) {
        try {
            File file = convertMultiPartToFile(multipartFile);
            String extension = FilenameUtils.getExtension(file.getName()); // returns "exe"

            String filePath = "assets/" + type + "/" + uuid + "." + extension;
            uploadCoverImageTos3bucket(filePath, file);
            file.delete();
            return endpointUrl + "/" + bucketName + "/" + filePath;
        } catch (Exception e) {
            throw new CustomInternalServerException("Asset upload failed.");
        }
    }

    private void uploadCoverImageTos3bucket(String filePath, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, filePath, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
