CREATE TABLE instructors
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    bio TEXT,
    facebook_profile TEXT,
    linkedin_profile TEXT,
    profile_picture TEXT,
    created_at TIMESTAMP WITH TIME ZONE,
    created_by BIGINT,
    updated_at TIMESTAMP WITH TIME ZONE,
    updated_by BIGINT,
    deleted_at TIMESTAMP WITH TIME ZONE,
    deleted_by BIGINT
);

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
    CONSTRAINT groups FOREIGN KEY (id)
        REFERENCES public.groups (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT instructors FOREIGN KEY (id)
        REFERENCES public.instructors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE training_instructors
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    training_id BIGINT NOT NULL,
    instructor_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE,
    created_by BIGINT,
    updated_at TIMESTAMP WITH TIME ZONE,
    updated_by BIGINT,
    deleted_at TIMESTAMP WITH TIME ZONE,
    deleted_by BIGINT,
    CONSTRAINT instructors FOREIGN KEY (instructor_id)
        REFERENCES public.instructors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT trainings FOREIGN KEY (training_id)
        REFERENCES public.trainings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);