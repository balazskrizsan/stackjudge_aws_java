package com.kbalazsworks.stackjudge_aws.s3.requests;

import lombok.Getter;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;

@Getter
public class GetUploadRequest
{
    @RestQuery
    List<String> ids;
}
