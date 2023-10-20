package com.kbalazsworks.stackjudge_aws.s3.controllers.upload_controller;

import com.kbalazsworks.stackjudge_aws.common.builders.ResponseEntityBuilder;
import com.kbalazsworks.stackjudge_aws.common.entities.ApiResponseData;
import com.kbalazsworks.stackjudge_aws.common.exceptions.ApiException;
import com.kbalazsworks.stackjudge_aws.s3.requests.PostUploadRequest;
import com.kbalazsworks.stackjudge_aws.s3.services.CdnService;
import com.kbalazsworks.stackjudge_aws.s3.services.RequestMapperService;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.CdnServicePutResponse;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.Put;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path(S3UploadRouting.POST_ROUTE)
@AllArgsConstructor
@Slf4j
public class PostUploadAction
{
    public final RequestMapperService requestMapperService;
    public final CdnService           cdnService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestBody(
        content = @Content(
            schema = @Schema(implementation = PostUploadRequest.class)
        )
    )
    public ApiResponseData<CdnServicePutResponse> postSendAction(@BeanParam PostUploadRequest request)
    throws ApiException
    {
        Put mappedRequest = requestMapperService.map(request);

        log.info("API call: {}", mappedRequest);

        CdnServicePutResponse awsResponse = cdnService.put(mappedRequest);

        log.info("AWS response: {}", awsResponse);

        return new ResponseEntityBuilder<CdnServicePutResponse>()
            .data(awsResponse)
            .build();
    }
}
