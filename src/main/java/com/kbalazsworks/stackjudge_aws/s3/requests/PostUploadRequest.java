package com.kbalazsworks.stackjudge_aws.s3.requests;

import lombok.Getter;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Getter
public class PostUploadRequest
{
    @RestForm
    String cdnNamespace;

    @RestForm
    String subFolder;

    @RestForm
    String fileName;

    @RestForm
    String fileExtension;

    @RestForm("content")
    FileUpload content;
}
