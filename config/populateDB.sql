DELETE FROM FLOWERSHOP.ROLE;
DELETE FROM FLOWERSHOP.USER;
DELETE FROM FLOWERSHOP.FLOWER;

INSERT INTO FLOWERSHOP.USER (login, first_name, last_name, password, address, phone_number, money_balance, discount)
VALUES ('admin', 'John', 'Smith', 'admin123', 'г.Тверь', '2345',  1000000, 15),
       ('user1', 'userFirstName1', 'userLastName1', 'password1', 'г.Тверь', '2345', 2000, 10),
       ('user2', 'userFirstName2', 'userLastName2', 'password2', 'г.Тверь', '2345', 2000, 5),
       ('user3', 'userFirstName3', 'userLastName3', 'password3', 'г.Тверь', '2345', 2000, 20),
       ('user4', 'userFirstName4', 'userLastName4', 'password4', 'г.Тверь', '2345', 2000, 5),
       ('user5', 'userFirstName5', 'userLastName5', 'password5', 'г.Тверь', '2345', 2000, 2);

INSERT INTO FLOWERSHOP.ROLE (user_login, role)
VALUES ('admin', 'ROLE_ADMIN'),
       ('user1', 'ROLE_USER'),
       ('user2', 'ROLE_USER'),
       ('user3', 'ROLE_USER'),
       ('user4', 'ROLE_USER'),
       ('user5', 'ROLE_USER');


INSERT INTO FLOWERSHOP.FLOWER (id, name, price, QUANTITY)
VALUES (FLOWER_SEQ.NEXTVAL, 'Тюльпан',  100, 15),
       (FLOWER_SEQ.NEXTVAL, 'Роза',     500, 100),
       (FLOWER_SEQ.NEXTVAL, 'Лопух',    130, 70),
       (FLOWER_SEQ.NEXTVAL, 'Борщевик', 210, 120),
       (FLOWER_SEQ.NEXTVAL, 'Ромашка',  90,  30),
       (FLOWER_SEQ.NEXTVAL, 'Георгин',  380, 42),
       (FLOWER_SEQ.NEXTVAL, 'Гвоздика', 40,  214)
