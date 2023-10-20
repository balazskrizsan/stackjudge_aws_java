package com.kbalazsworks.stackjudge_aws.s3.controllers.upload_controller;

import com.kbalazsworks.stackjudge_aws.common.builders.ResponseEntityBuilder;
import com.kbalazsworks.stackjudge_aws.common.entities.ApiResponseData;
import com.kbalazsworks.stackjudge_aws.common.exceptions.ApiException;
import com.kbalazsworks.stackjudge_aws.s3.entities.RemoteFile;
import com.kbalazsworks.stackjudge_aws.s3.requests.GetUploadRequest;
import com.kbalazsworks.stackjudge_aws.s3.services.RemoteFileService;
import com.kbalazsworks.stackjudge_aws.s3.services.RequestMapperService;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.UploadRequest;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Path(S3UploadRouting.GET_ROUTE)
@AllArgsConstructor
@Slf4j
public class GetUploadAction
{
    public final RequestMapperService requestMapperService;
    public final RemoteFileService    remoteFileService;

    @GET
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponseData<List<RemoteFile>> postSendAction(
        @BeanParam
        GetUploadRequest request
    )
    throws ApiException
    {
        UploadRequest mappedRequest = requestMapperService.map(request);

        log.info("API call: {}", mappedRequest);

        return new ResponseEntityBuilder<List<RemoteFile>>()
            .data(remoteFileService.getRemoteFiles(mappedRequest))
            .build();
    }
}
