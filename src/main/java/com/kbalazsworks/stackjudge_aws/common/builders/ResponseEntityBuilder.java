package com.kbalazsworks.stackjudge_aws.common.builders;

import com.kbalazsworks.stackjudge_aws.common.entities.ApiResponseData;
import com.kbalazsworks.stackjudge_aws.common.exceptions.ApiException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.http.HttpStatus;

@Accessors(fluent = true)
@Getter
@Setter
public class ResponseEntityBuilder<T>
{
    private T           data;
    private int         errorCode  = 0;
    private int         statusCode = HttpStatus.SC_OK;
//    private HttpHeaders headers    = new HttpHeaders(); //@todo: impelement

    public ApiResponseData<T> build() throws ApiException
    {
        Boolean success = errorCode == 0;

        if (errorCode > 0 && statusCode == HttpStatus.SC_OK)
        {
            throw new ApiException("Status code setup is needed for error response");
        }

        return new ApiResponseData<T>(data, success, statusCode, "not-implemented");
    }
}
