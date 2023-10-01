package com.kbalazsworks.stackjudge_aws.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kbalazsworks.simple_oidc.DiConfigModule;
import com.kbalazsworks.simple_oidc.services.HttpClientService;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class OidcConfig
{
    @Getter
    private Injector injector;

    void onStart(@Observes StartupEvent ev)
    {
        log.info("Simple OIDC config set");

        HttpClientService.host = "https://localhost:43011";

        injector = Guice.createInjector(new DiConfigModule());
    }
}
