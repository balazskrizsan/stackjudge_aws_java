package com.kbalazsworks.stackjudge_aws.common.services;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
@AllArgsConstructor
public class ConnectionService
{
    private final DataSource dataSource;

    public Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }
}
