CREATE TABLE emails(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    recipients TEXT,
    subject TEXT,
    body TEXT,
    template_file TEXT,
    template_data TEXT,
    created_at TIMESTAMP WITH TIME ZONE,
    created_by BIGINT,
    updated_at TIMESTAMP WITH TIME ZONE,
    updated_by BIGINT,
    deleted_at TIMESTAMP WITH TIME ZONE,
    deleted_by BIGINT
);