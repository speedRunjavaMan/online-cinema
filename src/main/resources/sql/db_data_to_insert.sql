--����
insert into roles
values (1, '���� ������������', 'USER'),
       (2, '���� ������������', 'LIBRARIAN');
--BOOKS
truncate table films cascade;
INSERT INTO films (id, created_by, created_when, genre, title, country, online_copy_path, premier_year)
VALUES (nextval('films_seq'), 'admin', '2022-11-15 13:54:28.115079', 0, '������� ����', '���', null, '1999-12-06');
INSERT INTO films (id, created_by, created_when, genre, title, country, online_copy_path, premier_year)
VALUES (nextval('films_seq'), 'admin', '2021-10-15 13:44:28.115079', 2, '������������ �����', '���', null, '1994-05-21');