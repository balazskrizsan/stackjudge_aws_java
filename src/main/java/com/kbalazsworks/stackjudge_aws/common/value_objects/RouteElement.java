package com.kbalazsworks.stackjudge_aws.common.value_objects;

import java.util.List;

public record RouteElement(String name, String route, List<String> allowedScopes, boolean requireAuth)
{
    public RouteElement(String name, String route, List<String> allowedScopes)
    {
        this(name, route, allowedScopes, true);
    }
}
