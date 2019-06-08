-------------------------------------------------------
--  Drop Tables
-------------------------------------------------------

DROP TABLE IF EXISTS FLOWERSHOP.USER;
DROP TABLE IF EXISTS FLOWERSHOP.ROLE;
DROP TABLE IF EXISTS FLOWERSHOP.FLOWER;

-------------------------------------------------------
--  Drop Sequences
-------------------------------------------------------

DROP sequence IF EXISTS FLOWERSHOP.FLOWER_SEQ;

-------------------------------------------------------
--  Create Sequences
-------------------------------------------------------

CREATE sequence FLOWER_SEQ;

--------------------------------------------------------
--  DDL for Table USER
--------------------------------------------------------

create table FLOWERSHOP.USER
(
    login varchar not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    password varchar(20) not null,
    address varchar(50),
    phone_number varchar(12) not null,
    money_balance decimal(8,2) not null,
    discount int not null,
    constraint USER_pk primary key (login)
);

create unique index USER_login_password_index
    on FLOWERSHOP.USER (login, password);

--------------------------------------------------------
--  DDL for Table ROLE
--------------------------------------------------------

create table FLOWERSHOP.ROLE
(
    user_login varchar not null,
    role varchar(10) not null,
    constraint role_user_login_fk
        foreign key (user_login) references FLOWERSHOP.USER (login)
            on delete cascade
);

create unique index ROLE_user_login_role_index
    on FLOWERSHOP.ROLE (user_login, role);

--------------------------------------------------------
--  DDL for Table FLOWER
--------------------------------------------------------

create table FLOWERSHOP.FLOWER
(
    id int not null,
    name varchar(30) not null,
    price int not null,
    quantity int not null,
    constraint FLOWER_pk primary key (id)
);

create table FLOWERSHOP."ORDER"
(
    ID INTEGER not null,
    USER_ID INTEGER not null,
    CREATE_DATA DATE not null,
    CLOSE_DATA DATE not null,
    STATUS VARCHAR(6) not null,
    constraint ORDER_PK
        primary key (ID),
    constraint ORDER_USER_LOGIN_FK
        foreign key (USER_ID) references FLOWERSHOP.USER
            on delete cascade
);
