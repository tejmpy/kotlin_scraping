CREATE TABLE sales_status (
    calendar_date DATE NOT NULL,
    tdl_status VARCHAR(15) NOT NULL,
    tds_status VARCHAR(15) NOT NULL,
    PRIMARY KEY(calendar_date)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
