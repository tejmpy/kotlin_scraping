CREATE TABLE user (
  id BINARY(16) NOT NULL,
  name VARCHAR(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;