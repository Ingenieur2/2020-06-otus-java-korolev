create table users
(
    id       bigint              not null auto_increment,
    name     varchar(255) unique not null,
    age      int                 not null,
    login    varchar(255) unique not null,
    password varchar(255)        not null,
    primary key (id)
);
