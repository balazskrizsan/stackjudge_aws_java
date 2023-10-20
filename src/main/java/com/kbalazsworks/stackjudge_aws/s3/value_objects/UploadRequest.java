package com.kbalazsworks.stackjudge_aws.s3.value_objects;

import java.util.List;

public record UploadRequest(List<String> ids)
{
}
