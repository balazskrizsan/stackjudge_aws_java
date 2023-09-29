package com.kbalazsworks.stackjudge_aws.s3.value_objects;

public record CdnServicePutResponse(String path, String fileName, String s3eTag, String s3contentMd5)
{
}
