--РОЛИ
insert into roles
values (1, 'Роль пользователя', 'USER'),
       (2, 'Роль библиотекаря', 'LIBRARIAN');
--BOOKS
truncate table films cascade;
INSERT INTO films (id, created_by, created_when, genre, title, country, online_copy_path, premier_year)
VALUES (nextval('films_seq'), 'admin', '2022-11-15 13:54:28.115079', 0, 'Зеленая Миля', 'США', null, '1999-12-06');
INSERT INTO films (id, created_by, created_when, genre, title, country, online_copy_path, premier_year)
VALUES (nextval('films_seq'), 'admin', '2021-10-15 13:44:28.115079', 2, 'Криминальное чтиво', 'США', null, '1994-05-21');