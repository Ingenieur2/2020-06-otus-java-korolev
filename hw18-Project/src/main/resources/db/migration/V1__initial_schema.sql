create table users
(
    id       bigserial           not null primary key,
    login    varchar(255) unique not null,
    password varchar(255)        not null
);
insert into users (login, password)
values ('admin', 'admin');
insert into users (login, password)
values ('user', 'user');

create table questions
(
    id       bigserial           not null primary key,
    question varchar(255) unique not null
);
insert into questions (question)
values ('2+2=?');
insert into questions (question)
values ('В каком году была куликовская битва?');
insert into questions (question)
values ('x*x = 16. x=?');
insert into questions (question)
values ('Если на объект ссылается только эта ссылка, то он удалится при первой сборке мусора:');

create table right_answers
(
    id          bigserial    not null primary key,
    answer      varchar(255) not null,
    question_id bigserial    not null,
    checkbox    boolean
);
insert into right_answers (answer, question_id, checkbox)
values ('4', 1, true);
insert into right_answers (answer, question_id, checkbox)
values ('0', 1, false);
insert into right_answers (answer, question_id, checkbox)
values ('3', 1, false);
insert into right_answers (answer, question_id, checkbox)
values ('8', 1, false);

insert into right_answers (answer, question_id, checkbox)
values ('1240', 2, false);
insert into right_answers (answer, question_id, checkbox)
values ('1530', 2, false);
insert into right_answers (answer, question_id, checkbox)
values ('1380', 2, true);
insert into right_answers (answer, question_id, checkbox)
values ('1480', 2, false);

insert into right_answers (answer, question_id, checkbox)
values ('8', 3, false);
insert into right_answers (answer, question_id, checkbox)
values ('4', 3, true);
insert into right_answers (answer, question_id, checkbox)
values ('64', 3, false);
insert into right_answers (answer, question_id, checkbox)
values ('-4', 3, true);

insert into right_answers (answer, question_id, checkbox)
values ('Weak', 4, true);
insert into right_answers (answer, question_id, checkbox)
values ('Soft', 4, false);
insert into right_answers (answer, question_id, checkbox)
values ('Phantom', 4, false);
insert into right_answers (answer, question_id, checkbox)
values ('Gc', 4, false);


create table user_answers
(
    id          bigserial not null primary key,
    user_id     bigserial not null,
    question_id bigserial not null,
    answer_id   bigserial not null,
    checkbox    boolean
)