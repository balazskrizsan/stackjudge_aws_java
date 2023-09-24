package com.kbalazsworks.stackjudge_aws.s3.services;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.kbalazsworks.stackjudge_aws.common.exceptions.RecordNotFoundException;
import com.kbalazsworks.stackjudge_aws.common.factories.LocalDateTimeFactory;
import com.kbalazsworks.stackjudge_aws.common.services.ApplicationPropertiesService;
import com.kbalazsworks.stackjudge_aws.common.services.DateTimeFormatterService;
import com.kbalazsworks.stackjudge_aws.s3.repositories.RemoteFileRepository;
import com.kbalazsworks.stackjudge_aws.s3.repositories.S3Repository;
import com.kbalazsworks.stackjudge_aws.s3.responses.PutAndSaveResponse;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.CdnServicePutResponse;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.Put;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.io.File;

@ApplicationScoped
@AllArgsConstructor
public class CdnService
{
    private final DateTimeFormatterService     dateTimeFormatterService;
    private final ApplicationPropertiesService applicationPropertiesService;
    private final S3Repository                 s3Repository;
    private final RemoteFileRepository         remoteFileRepository;
    private final LocalDateTimeFactory         localDateTimeFactory;

    public CdnServicePutResponse put(@NonNull Put put)
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

    public PutAndSaveResponse putAndSave(@NonNull Put put) throws RecordNotFoundException
    {
        CdnServicePutResponse putResponse = put(put);

        return new PutAndSaveResponse(
            remoteFileRepository.save(
                MapperService.mapCdnServicePutResponseToRemoteFile(putResponse)
            )
        );
    }
}
