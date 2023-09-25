CREATE TABLE remote_files
(
    id             uuid DEFAULT gen_random_uuid (),
    path           TEXT,
    filename       VARCHAR(255),
    s3_e_tag       VARCHAR(255),
    s3_content_md5 VARCHAR(255)
);
