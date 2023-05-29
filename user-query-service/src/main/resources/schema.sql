drop table IF EXISTS users;
drop table IF EXISTS user_audit;

create TABLE users
(
    id                  VARCHAR(100) PRIMARY KEY,
    first_name            VARCHAR(200),
    last_name            VARCHAR(256),
    date_of_birth         VARCHAR(50),
    gender              VARCHAR(15),
    age                 VARCHAR(50)
);