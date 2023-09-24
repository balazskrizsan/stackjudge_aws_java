package com.kbalazsworks.stackjudge_aws.s3.repositories;

import com.kbalazsworks.stackjudge_aws.common.exceptions.RecordNotFoundException;
import com.kbalazsworks.stackjudge_aws.common.repositories.AbstractRepository;
import com.kbalazsworks.stackjudge_aws.s3.entities.RemoteFile;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NonNull;

@ApplicationScoped
public class RemoteFileRepository extends AbstractRepository
{
    private final com.kbalazsworks.stackjudge_aws.db.tables.RemoteFiles remoteFilesTable =
        com.kbalazsworks.stackjudge_aws.db.tables.RemoteFiles.REMOTE_FILES;

    public RemoteFile save(@NonNull RemoteFile remoteFile) throws RecordNotFoundException
    {
        return recordReturnValidator(
            getQueryBuilder()
                .insertInto(
                    remoteFilesTable,
                    remoteFilesTable.PATH,
                    remoteFilesTable.FILENAME,
                    remoteFilesTable.S3_E_TAG,
                    remoteFilesTable.S3_CONTENT_MD5
                )
                .values(
                    remoteFile.path(),
                    remoteFile.filename(),
                    remoteFile.s3ETag(),
                    remoteFile.s3ContentMd5()
                )
                .returningResult()
                .fetchOneInto(RemoteFile.class)
        );
    }
}
