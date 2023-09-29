package com.kbalazsworks.stackjudge_aws.s3.factories;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.kbalazsworks.stackjudge_aws.common.services.ApplicationPropertiesService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AmazonS3ClientFactory
{
    @Inject
    ApplicationPropertiesService applicationPropertiesService;

    public AmazonS3 create()
    {
        return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.EU_CENTRAL_1)
            .withCredentials(
                new AWSStaticCredentialsProvider(
                    new BasicAWSCredentials(
                        applicationPropertiesService.getAwsAccessKey(),
                        applicationPropertiesService.getAwsSecretKey()
                    )
                )
            )
            .build();
    }
}
