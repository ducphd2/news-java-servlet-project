use news_project_servlet;

insert into roles(code, name)
values ('admin', 'Administrator');
insert into roles(code, name)
values ('user', 'User');

insert into users(username, password, full_name, status, role_id)
values ('admin', '123456', 'DucPH', 1, 1);
insert into users(username, password, full_name, status, role_id)
values ('ducph', '123456', 'Đức Phạm Huỳnh', 1, 2);
insert into users(username, password, full_name, status, role_id)
values ('duc', '123456', 'Đức Phạm', 1, 2);