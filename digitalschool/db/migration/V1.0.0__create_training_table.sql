CREATE TABLE trainings
(
    id                  BIGSERIAL PRIMARY KEY,
    title               TEXT,
    description         TEXT,
    price               NUMERIC,
    cover_image         BYTEA,
    cover_image_filename    TEXT,
    created_at          TIMESTAMP WITH TIME ZONE,
    created_by          BIGINT,
    updated_at          TIMESTAMP WITH TIME ZONE,
    updated_by          BIGINT,
    deleted_at          TIMESTAMP WITH TIME ZONE,
    deleted_by          BIGINT
);
