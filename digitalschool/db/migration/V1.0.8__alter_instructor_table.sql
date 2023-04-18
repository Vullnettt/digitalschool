ALTER TABLE instructors
DROP COLUMN profile_picture;

ALTER TABLE instructors
ADD profile_picture BYTEA;