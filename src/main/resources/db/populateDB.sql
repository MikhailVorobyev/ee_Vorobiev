DELETE FROM FLOWERSHOP.USER;
DELETE FROM FLOWERSHOP.FLOWER;

INSERT INTO FLOWERSHOP.USER (login, first_name, last_name, password, address, phone_number, money_balance, discount, role)
VALUES ('admin', 'John', 'Smith', 'admin123', 'г.Тверь', '2345',  1000000, 15, 'ROLE_ADMIN'),
       ('user1', 'userFirstName1', 'userLastName1', 'password1', 'г.Тверь', '2345', 2000, 10, 'ROLE_USER'),
       ('user2', 'userFirstName2', 'userLastName2', 'password2', 'г.Тверь', '2345', 2000, 5,  'ROLE_USER'),
       ('user3', 'userFirstName3', 'userLastName3', 'password3', 'г.Тверь', '2345', 2000, 20, 'ROLE_USER'),
       ('user4', 'userFirstName4', 'userLastName4', 'password4', 'г.Тверь', '2345', 2000, 5,  'ROLE_USER'),
       ('user5', 'userFirstName5', 'userLastName5', 'password5', 'г.Тверь', '2345', 2000, 2,  'ROLE_USER');


INSERT INTO FLOWERSHOP.FLOWER (id, name, price, QUANTITY)
VALUES (FLOWERSHOP.FLOWER_SEQ.NEXTVAL, 'Tulip',     100, 15),
       (FLOWERSHOP.FLOWER_SEQ.NEXTVAL, 'Rose',      500, 100),
       (FLOWERSHOP.FLOWER_SEQ.NEXTVAL, 'Burdock',   130, 70),
       (FLOWERSHOP.FLOWER_SEQ.NEXTVAL, 'Hogweed',   210, 120),
       (FLOWERSHOP.FLOWER_SEQ.NEXTVAL, 'Chamomile', 90,  30),
       (FLOWERSHOP.FLOWER_SEQ.NEXTVAL, 'Dahlia',    380, 42),
       (FLOWERSHOP.FLOWER_SEQ.NEXTVAL, 'Carnation', 40,  214);
