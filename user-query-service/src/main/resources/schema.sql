drop table IF EXISTS users;
drop table IF EXISTS user_audit;

create TABLE users
(
    id                  VARCHAR(100) PRIMARY KEY,
    firstName            VARCHAR(200),
    lastName            VARCHAR(256),
    dateOfBirth         VARCHAR(50),
    gender              VARCHAR(15),
    age               VARCHAR(50)
);