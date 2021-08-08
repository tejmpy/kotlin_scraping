CREATE TABLE notification (
    user_id BINARY(16) NOT NULL,
    notification_date DATE NOT NULL,
    PRIMARY KEY(user_id, notification_date),
    CONSTRAINT nd_userid_to_user
        FOREIGN KEY (user_id)
            REFERENCES user (id)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
