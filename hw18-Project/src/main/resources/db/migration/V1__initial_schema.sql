create table users
(
    id       bigserial           not null primary key,
    login    varchar(255) unique not null,
    password varchar(255)        not null,
    answer text
);
insert into users (login, password) values ('admin' , 'admin');

create table questions
(
    question_id       bigserial          not null primary key,
    theme    varchar(255) unique not null,
    answer1  varchar(255)       not null,
    checkbox1 boolean,
    answer2  varchar(255)       not null,
    checkbox2 boolean,
    answer3  varchar(255)       not null,
    checkbox3 boolean,
    answer4  varchar(255)       not null,
    checkbox4 boolean
);
insert into questions ( theme,  answer1,  checkbox1, answer2,  checkbox2,  answer3,  checkbox3, answer4,  checkbox4) values
                      ('2+2=?', '4',    true,  '0',  false,    '3',   false,  '8',   false);
insert into questions ( theme,  answer1,  checkbox1, answer2,  checkbox2,  answer3,  checkbox3, answer4,  checkbox4) values
                      ('В каком году была куликовская битва?', '1240',    false,  '1530',  false,    '1380',   true,  '1480',   false);
insert into questions ( theme,  answer1,  checkbox1, answer2,  checkbox2,  answer3,  checkbox3, answer4,  checkbox4) values
                      ('x*x = 16. x=?', '8',    false,  '4',  true,    '64',   true,  '-4',   true);
insert into questions ( theme,  answer1,  checkbox1, answer2,  checkbox2,  answer3,  checkbox3, answer4,  checkbox4) values
                      ('Если на объект ссылается только эта ссылка, то он удалится при первой сборке мусора:', 'Weak',    true,  'Soft',  false,    'Phantom',   false,  'Gc',   false);