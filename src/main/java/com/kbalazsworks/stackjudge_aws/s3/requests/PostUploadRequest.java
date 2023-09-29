package com.kbalazsworks.stackjudge_aws.s3.requests;

import jakarta.enterprise.inject.Default;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.ToString;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

//public class PostUploadRequest(
//    @Default
//    @RestForm
//    @PartType(MediaType.TEXT_PLAIN)
//    String cdnNamespace,
//
//    @RestForm
//    @PartType(MediaType.TEXT_PLAIN)
//    String subFolder,
//
//    @RestForm
//    @PartType(MediaType.TEXT_PLAIN)
//    String fileName,
//
//    @RestForm
//    @PartType(MediaType.TEXT_PLAIN)
//    String fileExtension,
//
//    @RestForm("content")
//    FileUpload content
//)
//{
//}

@Getter
public class PostUploadRequest {
    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    @Getter
    String cdnNamespace;

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    String subFolder;

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    String fileName;

    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    String fileExtension;

    @RestForm("content")
    FileUpload content;
}
