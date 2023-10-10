CREATE TABLE message
(
    id         UUID DEFAULT uuid_generate_v4(),
    user_id    UUID         NOT NULL,
    message    VARCHAR(512) NOT NULL,
    CREATED_AT TIMESTAMP    NOT NULL,
    UPDATED_AT TIMESTAMP    NULL,
    PRIMARY KEY (id)
);