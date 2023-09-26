package com.kbalazsworks.stackjudge_aws.common.services;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;

@ApplicationScoped
@AllArgsConstructor
public class JooqService
{
    private final ConnectionService connectionService;

    @SneakyThrows
    public DSLContext getDbContext()
    {
        return new DefaultDSLContext(connectionService.getConnection(), SQLDialect.POSTGRES, null);
    }
}
