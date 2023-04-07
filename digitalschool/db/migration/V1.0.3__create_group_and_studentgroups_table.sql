CREATE TABLE groups
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    title           TEXT NOT NULL,
    description     text NOT NULL,
    start_date      TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date        TIMESTAMP WITH TIME ZONE NOT NULL,
    training_id     BIGINT NOT NULL,
    CONSTRAINT trainings FOREIGN KEY (training_id)
                    REFERENCES public.trainings (id) MATCH SIMPLE
                    ON UPDATE NO ACTION
                    ON DELETE NO ACTION
                    NOT VALID,
    created_at      TIMESTAMP WITH TIME ZONE,
    created_by      BIGINT,
    updated_at      TIMESTAMP WITH TIME ZONE,
    updated_by      BIGINT,
    deleted_at      TIMESTAMP WITH TIME ZONE,
    deleted_by      BIGINT
);

CREATE TABLE student_groups
(
    id              BIGSERIAL NOT NULL PRIMARY KEY,
    student_id      BIGINT NOT NULL,
    group_id        BIGINT NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE,
    created_by      BIGINT,
    updated_at      TIMESTAMP WITH TIME ZONE,
    updated_by      BIGINT,
    deleted_at      TIMESTAMP WITH TIME ZONE,
    deleted_by      BIGINT,
    CONSTRAINT groups FOREIGN KEY (group_id)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT students FOREIGN KEY (student_id)
        REFERENCES public.students (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);