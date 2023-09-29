package com.kbalazsworks.stackjudge_aws.common.factories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ApplicationScoped
public class LocalDateTimeFactory
{
    @Inject
    DateFactory dateFactory;

    public LocalDateTime create()
    {
        return dateFactory.create().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
