package com.kbalazsworks.stackjudge_aws.s3.requests;

import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;

import java.util.List;

@Getter
public class GetUploadRequest
{
    @RestForm
    @PartType(MediaType.TEXT_PLAIN)
    List<String> ids;
}
