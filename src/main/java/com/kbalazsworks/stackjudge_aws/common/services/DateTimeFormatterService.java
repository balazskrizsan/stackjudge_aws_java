package com.kbalazsworks.stackjudge_aws.common.services;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ApplicationScoped
public class DateTimeFormatterService
{
    public long toEpoch(LocalDateTime now)
    {
        return now.toEpochSecond(ZoneOffset.UTC);
    }
}
