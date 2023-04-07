CREATE TABLE students
(
    id              BIGSERIAL PRIMARY KEY,
    first_name      TEXT NOT NULL,
    last_name       TEXT NOT NULL,
    email           TEXT NOT NULL,
    phone_number    TEXT,
    personal_number TEXT NOT NULL,
    profile_picture TEXT,
    created_at      TIMESTAMP WITH TIME ZONE,
    created_by      BIGINT,
    updated_at      TIMESTAMP WITH TIME ZONE,
    updated_by      BIGINT,
    deleted_at      TIMESTAMP WITH TIME ZONE,
    deleted_by      BIGINT
);