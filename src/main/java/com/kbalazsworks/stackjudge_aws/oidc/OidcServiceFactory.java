package com.kbalazsworks.stackjudge_aws.oidc;

import com.kbalazsworks.simple_oidc.services.ICommunicationService;
import com.kbalazsworks.simple_oidc.services.IValidationService;
import com.kbalazsworks.stackjudge_aws.config.OidcConfig;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class OidcServiceFactory
{
    private final OidcConfig oidcConfig;

    public ICommunicationService getCommunicationService()
    {
        return oidcConfig.getInjector().getInstance(ICommunicationService.class);
    }

    public IValidationService getValidationService()
    {
        return oidcConfig.getInjector().getInstance(IValidationService.class);
    }
}
