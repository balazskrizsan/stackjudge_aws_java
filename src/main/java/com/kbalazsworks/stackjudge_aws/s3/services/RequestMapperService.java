package com.kbalazsworks.stackjudge_aws.s3.services;

import com.kbalazsworks.stackjudge_aws.s3.enums.CdnNamespaceEnum;
import com.kbalazsworks.stackjudge_aws.s3.requests.PostUploadRequest;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.Put;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@ApplicationScoped
public class RequestMapperService
{
    @SneakyThrows public Put map(PostUploadRequest request)
    {
        try
        {
            return new Put(
                CdnNamespaceEnum.valueOf(request.getCdnNamespace()),
                request.getSubFolder(),
                request.getFileName(),
                request.getFileExtension(),
                request.getContent().uploadedFile().toFile()
            );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        throw new Exception();
    }
}
