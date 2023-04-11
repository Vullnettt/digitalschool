DROP TABLE group_instructors;


CREATE TABLE group_instructors
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    group_id BIGINT NOT NULL,
    instructor_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    created_by BIGINT,
    updated_at TIMESTAMP WITH TIME ZONE,
    updated_by BIGINT,
    deleted_at TIMESTAMP WITH TIME ZONE,
    deleted_by BIGINT,
    CONSTRAINT groups FOREIGN KEY (group_id)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT instructors FOREIGN KEY (instructor_id)
        REFERENCES public.instructors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);