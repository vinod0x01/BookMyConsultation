package com.bookmyconsultation.doctorservice.model.entity;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class S3Repository {

    private AmazonS3 s3Client;
    private final String BUCKET_NAME = "bmc_bucket1";

    @Value("${s3.access_key}")
    private String accessKey;

    @Value("${s3.secret_key}")
    private String secretKey;

    @Autowired
    ObjectMetadata metadata;

    @PostConstruct
    public void init(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
        s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.US_EAST_1)
                    .build();
    }

    public void uploadFiles(String userId, MultipartFile file) throws IOException {

        String key = userId + "/" + file.getOriginalFilename();

        if(!s3Client.doesBucketExistV2(BUCKET_NAME)){
            s3Client.createBucket(BUCKET_NAME);
        }

        s3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
    }
}
