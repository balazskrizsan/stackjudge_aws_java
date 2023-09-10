package com.kbalazsworks.stackjudge_aws.common.filters;

import com.kbalazsworks.simple_oidc.exceptions.OidcException;
import com.kbalazsworks.stackjudge_aws.common.value_objects.RouteElement;
import com.kbalazsworks.stackjudge_aws.oidc.OidcServiceFactory;
import com.kbalazsworks.stackjudge_aws.s3.controllers.RouteValidatorMapper;
import jakarta.ws.rs.container.ContainerRequestContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

@AllArgsConstructor
@Slf4j
public class Filters
{
    public final OidcServiceFactory oidcServiceFactory;

    @ServerRequestFilter(preMatching = true)
    public void oidcTokenValidator(ContainerRequestContext requestContext) throws Exception
    {
        String uri = requestContext.getUriInfo().getPath();
        log.info("Authenticating on route: {}", uri);

        String authToken = requestContext.getHeaderString("Authorization");

        if (authToken == null || authToken.isEmpty())
        {
            throw new Exception("No authentication header present");
        }

        RouteElement currentRouteElement = RouteValidatorMapper.routeElements
            .stream()
            .filter(item -> item.route().equals(uri))
            .findAny()
            .orElse(null);

        if (currentRouteElement == null)
        {
            throw new Exception("No authentication configuration set for route: " + uri);
        }

        try
        {
            oidcServiceFactory
                .getValidationService()
                .checkScopesInToken(authToken, currentRouteElement.allowedScopes());
        }
        catch (Exception e)
        {
            log.error("SimpleOIDC Authentication error, {}", e.getMessage());

            throw new OidcException("Authentication error");
        }
    }
}
