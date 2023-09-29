package com.kbalazsworks.stackjudge_aws.s3.value_objects;

import com.kbalazsworks.stackjudge_aws.s3.enums.CdnNamespaceEnum;

import java.io.File;

public record Put(
    CdnNamespaceEnum cdnNamespaceEnum,
    String subFolder,
    String fileName,
    String fileExtension,
    File content
)
{
}
