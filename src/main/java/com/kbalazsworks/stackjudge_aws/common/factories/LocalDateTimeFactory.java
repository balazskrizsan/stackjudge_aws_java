package com.kbalazsworks.stackjudge_aws.common.factories;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ApplicationScoped
@AllArgsConstructor
public class LocalDateTimeFactory
{
    private final DateFactory dateFactory;

    public LocalDateTime create()
    {
        return dateFactory.create().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
