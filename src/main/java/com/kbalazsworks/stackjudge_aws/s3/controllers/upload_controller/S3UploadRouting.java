package com.kbalazsworks.stackjudge_aws.s3.controllers.upload_controller;

import java.util.List;

public class S3UploadRouting
{

    public static final String       POST_NAME           = "POST:/s3/upload/post";
    public static final String       POST_ROUTE          = "/s3/upload";
    public static final List<String> POST_ALLOWED_SCOPES = List.of("sj.aws");

    public static final String       POST_V2_NAME           = "POST:/v2/s3/upload/post";
    public static final String       POST_V2_ROUTE          = "/v2/s3/upload";
    public static final List<String> POST_V2_ALLOWED_SCOPES = List.of("sj.aws");
}
