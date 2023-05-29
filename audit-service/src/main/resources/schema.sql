drop table IF EXISTS users;
drop table IF EXISTS user_audit;

create TABLE users
(
    id                  VARCHAR(100) PRIMARY KEY,
    firstName            VARCHAR(200),
    lastName            VARCHAR(256),
    dateOfBirth         VARCHAR(50),
    gender              VARCHAR(15),
    age                 VARCHAR(50)
);

create TABLE user_audit
(
    id              VARCHAR PRIMARY KEY,
    createdDate     VARCHAR(50),
    modifiedDate    VARCHAR(50),
    createdBy       VARCHAR(50),
    modifiedBy      VARCHAR(50),
    type            VARCHAR(50),
    userId          VARCHAR(200)
);