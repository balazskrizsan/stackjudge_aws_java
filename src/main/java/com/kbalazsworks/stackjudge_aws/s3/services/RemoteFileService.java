package com.kbalazsworks.stackjudge_aws.s3.services;

import com.kbalazsworks.stackjudge_aws.s3.entities.RemoteFile;
import com.kbalazsworks.stackjudge_aws.s3.repositories.RemoteFileRepository;
import com.kbalazsworks.stackjudge_aws.s3.value_objects.UploadIds;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class RemoteFileService
{
    private final RemoteFileRepository remoteFileRepository;

    public List<RemoteFile> getRemoteFiles(@NonNull UploadIds uploadIds)
    {
        return remoteFileRepository.getRemoteFiles(uploadIds.ids());
    }
}
