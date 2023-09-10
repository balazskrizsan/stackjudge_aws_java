package com.kbalazsworks.stackjudge_aws.common.value_objects;

import java.util.List;

public record RouteElement(String name, String route, List<String> allowedScopes)
{
}
