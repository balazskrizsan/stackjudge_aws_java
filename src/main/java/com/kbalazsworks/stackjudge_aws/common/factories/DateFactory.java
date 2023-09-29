package com.kbalazsworks.stackjudge_aws.common.factories;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Date;

@ApplicationScoped
public class DateFactory
{
    public Date create()
    {
        return new Date();
    }

    public Date create(Long date)
    {
        return new Date(date);
    }
}
