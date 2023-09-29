package com.kbalazsworks.stackjudge_aws.s3.repositories;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.kbalazsworks.stackjudge_aws.s3.factories.AmazonS3ClientFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class S3Repository
{
    @Inject
    AmazonS3ClientFactory amazonS3ClientFactory;

    public PutObjectResult put(PutObjectRequest putObjectRequest)
    {
        return amazonS3ClientFactory.create().putObject(putObjectRequest);
    }
}
