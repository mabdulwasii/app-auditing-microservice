drop table IF EXISTS users;
drop table IF EXISTS user_audit;

create TABLE user_audit
(
    id              VARCHAR PRIMARY KEY,
    created_date     VARCHAR(50),
    modified_date    VARCHAR(50),
    created_by       VARCHAR(50),
    modified_by      VARCHAR(50),
    type             VARCHAR(50),
    payload          VARCHAR(20000)
);