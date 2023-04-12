CREATE TABLE disable_reason
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    disable_reason TEXT NOT NULL,
    instructor_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    created_by BIGINT,
    updated_at TIMESTAMP WITH TIME ZONE,
    updated_by BIGINT,
    deleted_at TIMESTAMP WITH TIME ZONE,
    deleted_by BIGINT,
    CONSTRAINT instructor FOREIGN KEY (instructor_id)
        REFERENCES public.instructors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);
