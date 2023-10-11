package com.kbalazsworks.stackjudge_aws.common;

import com.kbalazsworks.stackjudge_aws.common.value_objects.RouteElement;
import com.kbalazsworks.stackjudge_aws.s3.controllers.upload_controller.S3UploadRouting;

import java.util.ArrayList;
import java.util.List;

public class RouteValidatorMapper
{
    public static final List<RouteElement> routeElements = new ArrayList<>()
    {{
        add(new RouteElement(
            "openapi",
            "/openapi33",
            S3UploadRouting.POST_V2_ALLOWED_SCOPES,
            false
        ));
        add(new RouteElement(
            S3UploadRouting.POST_NAME,
            S3UploadRouting.POST_ROUTE,
            S3UploadRouting.POST_ALLOWED_SCOPES
        ));
        add(new RouteElement(
            S3UploadRouting.POST_V2_NAME,
            S3UploadRouting.POST_V2_ROUTE,
            S3UploadRouting.POST_V2_ALLOWED_SCOPES
        ));
    }};
}
