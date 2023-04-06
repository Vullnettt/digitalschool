CREATE TABLE subjects
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    title           TEXT NOT NULL,
    description     TEXT,
    created_at      TIMESTAMP WITH TIME ZONE,
    created_by      BIGINT,
    updated_at      TIMESTAMP WITH TIME ZONE,
    updated_by      BIGINT,
    deleted_at      TIMESTAMP WITH TIME ZONE,
    deleted_by      BIGINT,
    training_id     BIGINT NOT NULL,
    CONSTRAINT trainings FOREIGN KEY (training_id)
        REFERENCES public.trainings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);