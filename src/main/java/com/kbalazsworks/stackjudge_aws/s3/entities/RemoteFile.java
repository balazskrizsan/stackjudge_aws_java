package com.kbalazsworks.stackjudge_aws.s3.entities;

import java.util.UUID;

public record RemoteFile(
    UUID id,
    String path,
    String filename,
    String s3ETag,
    String s3ContentMd5
    )
{
}
