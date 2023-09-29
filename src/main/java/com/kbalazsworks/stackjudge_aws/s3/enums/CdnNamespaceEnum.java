package com.kbalazsworks.stackjudge_aws.s3.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum CdnNamespaceEnum
{
    STATIC("static"),
    COMPANY_LOGOS("company-logos"),
    STATIC_MAPS("static-maps");

    String value;

    CdnNamespaceEnum(String value)
    {
        this.value = value;
    }
}
