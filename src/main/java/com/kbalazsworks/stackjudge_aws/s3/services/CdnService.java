package com.kbalazsworks.stackjudge_aws.s3.services;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.kbalazsworks.stackjudge_aws.common.factories.LocalDateTimeFactory;
import com.kbalazsworks.stackjudge_aws.common.services.ApplicationPropertiesService;
import com.kbalazsworks.stackjudge_aws.common.services.DateTimeFormatterService;
import com.kbalazsworks.stackjudge_aws.s3.enums.CdnNamespaceEnum;
import com.kbalazsworks.stackjudge_aws.s3.repositories.S3Repository;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.CdnServicePutResponse;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.Put;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.File;

@ApplicationScoped
public class CdnService
{
    @Inject
    DateTimeFormatterService dateTimeFormatterService;

    @Inject
    LocalDateTimeFactory localDateTimeFactory;

    @Inject
    S3Repository s3Repository;

    @Inject
    ApplicationPropertiesService applicationPropertiesService;

    public CdnServicePutResponse put(Put put)
    {
        File           content        = put.content();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(content.length());
        var    unixTimestamp = dateTimeFormatterService.toEpoch(localDateTimeFactory.create());
        String fullFileName  = "/" + put.fileName() + "-" + unixTimestamp + "." + put.fileExtension();
        String pathAndFile   = put.cdnNamespaceEnum().getValue() + put.subFolder() + fullFileName;

        PutObjectResult putObjectResult = s3Repository.put(
            new PutObjectRequest(
                applicationPropertiesService.getAwsS3CdnBucket(),
                pathAndFile,
                content
            )
        );

        return new CdnServicePutResponse(
            pathAndFile,
            fullFileName,
            putObjectResult.getETag(),
            putObjectResult.getContentMd5()
        );
    }
}
