package com.kbalazsworks.stackjudge_aws.common.services;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Getter
public class ApplicationPropertiesService
{
    @ConfigProperty(name = "aws.access_key")
    String awsAccessKey;

    @ConfigProperty(name = "aws.secret_key")
    String awsSecretKey;

    @ConfigProperty(name = "aws.s3.cdn_bucket")
    String awsS3CdnBucket;

    @ConfigProperty(name = "keystore.full_path")
    String keystoreFullPath;
}
