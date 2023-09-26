package com.kbalazsworks.stackjudge_aws.common.repositories;

import com.kbalazsworks.stackjudge_aws.common.exceptions.RecordNotFoundException;
import com.kbalazsworks.stackjudge_aws.common.services.JooqService;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import org.jooq.DSLContext;

abstract public class AbstractRepository
{
    @Inject
    JooqService jooqService;

    protected DSLContext getQueryBuilder()
    {
        return jooqService.getDbContext();
    }

    protected <T> T recordReturnValidator(@Nullable T record)
    throws RecordNotFoundException
    {
        if (record != null)
        {
            return record;
        }

        throw new RecordNotFoundException();
    }
}
