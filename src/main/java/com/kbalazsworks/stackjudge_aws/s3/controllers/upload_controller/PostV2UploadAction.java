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
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

@Path("/v2/s3/upload")
@AllArgsConstructor
public class PostV2UploadAction
{
    public final RequestMapperService requestMapperService;
    public final CdnService           cdnService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponseData<CdnServicePutResponse> postSendAction(
        @BeanParam
        PostUploadRequest request
//        @RestHeader("Authorization")
//        String token
    ) throws ApiException
    {
        Put mappedRequest = requestMapperService.map(request);

        return new ResponseEntityBuilder<CdnServicePutResponse>()
            .data(cdnService.put(mappedRequest))
            .build();
    }
}
