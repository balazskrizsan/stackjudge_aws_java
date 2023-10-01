package com.kbalazsworks.stackjudge_aws.s3.controllers.upload_controller;

import com.kbalazsworks.simple_oidc.exceptions.OidcExpiredTokenException;
import com.kbalazsworks.simple_oidc.exceptions.OidcJwksVerificationException;
import com.kbalazsworks.simple_oidc.exceptions.OidcJwtParseException;
import com.kbalazsworks.simple_oidc.exceptions.OidcScopeException;
import com.kbalazsworks.stackjudge_aws.common.builders.ResponseEntityBuilder;
import com.kbalazsworks.stackjudge_aws.common.entities.ApiResponseData;
import com.kbalazsworks.stackjudge_aws.common.exceptions.ApiException;
import com.kbalazsworks.stackjudge_aws.common.exceptions.RecordNotFoundException;
import com.kbalazsworks.stackjudge_aws.oidc.OidcServiceFactory;
import com.kbalazsworks.stackjudge_aws.s3.requests.PostUploadRequest;
import com.kbalazsworks.stackjudge_aws.s3.responses.PutAndSaveResponse;
import com.kbalazsworks.stackjudge_aws.s3.services.CdnService;
import com.kbalazsworks.stackjudge_aws.s3.services.RequestMapperService;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.Put;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.ArrayList;
import java.util.List;


@Path("/v2/s3/upload")
@AllArgsConstructor
@Slf4j
public class PostV2UploadAction
{
    public final RequestMapperService requestMapperService;
    public final CdnService           cdnService;
    public final OidcServiceFactory   oidcServiceFactory;

    @POST
    public ApiResponseData<PutAndSaveResponse> postSendAction(
        @BeanParam
        PostUploadRequest request,
        @RestHeader("Authorization")
        String token
    ) throws
        RecordNotFoundException,
        ApiException,
        OidcJwtParseException,
        OidcExpiredTokenException,
        OidcJwksVerificationException,
        OidcScopeException
    {
        oidcServiceFactory.getValidationService().checkScopesInToken(token, List.of("sj.aws"));

        Put mappedRequest = requestMapperService.map(request);

        log.info("API call: {}", mappedRequest);

        PutAndSaveResponse awsResponse = cdnService.putAndSave(mappedRequest);

        log.info("AWS response: {}", awsResponse);

        return new ResponseEntityBuilder<PutAndSaveResponse>()
            .data(awsResponse)
            .build();
    }
}
