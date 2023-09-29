package com.kbalazsworks.stackjudge_aws.common.entities;

public record ApiResponseData<T>(
    T data,
    Boolean success,
    Integer errorCode,
    String requestId
)
{
}
