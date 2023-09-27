package com.kbalazsworks.stackjudge_aws.s3.repositories;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.kbalazsworks.stackjudge_aws.s3.factories.AmazonS3ClientFactory;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class S3Repository
{
    private final AmazonS3ClientFactory amazonS3ClientFactory;

    public PutObjectResult put(PutObjectRequest putObjectRequest)
    {
        return amazonS3ClientFactory.create().putObject(putObjectRequest);
    }
}
