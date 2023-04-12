--����
-- truncate table users;
-- truncate table roles cascade;
-- truncate table roles;
-- truncate table roles cascade;
insert into roles
values (1, '���� ������������', 'USER'),
       (2, '���� ������������', 'LIBRARIAN');
--FILMS
-- truncate table films_directors;
-- truncate table films_composers;
-- truncate table films cascade;
INSERT INTO films (id, created_by, created_when, genre, title, country, online_copy_path, premier_year, average_grade)
VALUES (nextval('films_seq'), 'admin', '2022-11-15 13:54:28.115079', 0, '������� ����', '���', null, '1999-12-06', 5.0);
INSERT INTO films (id, created_by, created_when, genre, title, country, online_copy_path, premier_year, average_grade)
VALUES (nextval('films_seq'), 'admin', '2021-10-15 13:44:28.115079', 2, '������������ �����', '���', null, '1994-05-21', 4.5);
INSERT INTO films (id, created_by, created_when, genre, title, country, online_copy_path, premier_year, average_grade)
VALUES (nextval('films_seq'), 'admin', '2021-12-15 13:44:28.115079', 3, '������� ����', '���', null, '1994-06-23', 4.8);
INSERT INTO films (id, created_by, created_when, genre, title, country, online_copy_path, premier_year, average_grade)
VALUES (nextval('films_seq'), 'admin', '2022-12-15 13:44:28.115079', 0, '������', '���', null, '2010-07-08', 4.3);
INSERT INTO films (id, created_by, created_when, genre, title, country, online_copy_path, premier_year, average_grade)
VALUES (nextval('films_seq'), 'admin', '2022-12-15 13:44:28.115079', 3, '1+1', '�������', null, '2011-09-23', 4.7);
--DIRECTORS
-- truncate table directors cascade;
INSERT INTO directors (id, created_by, created_when, directors_fio, position)
VALUES (nextval('directors_seq'), 'admin', '2022-11-15 13:54:28.115079', '����� ��������', '�������');
INSERT INTO directors (id, created_by, created_when, directors_fio, position)
VALUES (nextval('directors_seq'), 'admin', '2022-11-15 13:54:28.115079', '������ �������', '�������');
INSERT INTO directors (id, created_by, created_when, directors_fio, position)
VALUES (nextval('directors_seq'), 'admin', '2022-11-15 13:54:28.115079', '��������� �����', '�������');
INSERT INTO directors (id, created_by, created_when, directors_fio, position)
VALUES (nextval('directors_seq'), 'admin', '2022-11-15 13:54:28.115079', '������� ���������', '������');
INSERT INTO directors (id, created_by, created_when, directors_fio, position)
VALUES (nextval('directors_seq'), 'admin', '2022-11-15 13:54:28.115079', '������� �����', '�������, �����');
--COMPOSERS
-- truncate table composers cascade;
INSERT INTO composers (id, created_by, created_when, composers_fio, position)
VALUES (nextval('composers_seq'), 'admin', '2022-11-15 13:54:28.115079', '���� ������', '������');
INSERT INTO composers (id, created_by, created_when, composers_fio, position)
VALUES (nextval('composers_seq'), 'admin', '2021-11-15 13:54:28.115079', '����� ������', '�������');
INSERT INTO composers (id, created_by, created_when, composers_fio, position)
VALUES (nextval('composers_seq'), 'admin', '2020-11-15 13:54:28.115079', '�������� �������', '�����������');
INSERT INTO composers (id, created_by, created_when, composers_fio, position)
VALUES (nextval('composers_seq'), 'admin', '2020-11-15 13:54:28.115079', '���� ����������', '�������');
INSERT INTO composers (id, created_by, created_when, composers_fio, position)
VALUES (nextval('composers_seq'), 'admin', '2021-11-15 13:54:28.115079', '����������� �����', '�������, �����');