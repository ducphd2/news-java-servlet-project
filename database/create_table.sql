use news_project_servlet;

create table if not exists roles
(
    id            bigint       not null primary key auto_increment,
    name          varchar(255) not null,
    code          varchar(255) not null,
    created_date  timestamp    null,
    modified_date timestamp    null,
    created_by    varchar(255) null,
    modified_by   varchar(255) null
);

create table if not exists users
(
    id            bigint       not null primary key auto_increment,
    username      varchar(255) not null,
    full_name     varchar(255) null,
    password      varchar(255) not null,
    status        int          not null,
    role_id       bigint       not null,
    created_date  timestamp    null,
    modified_date timestamp    null,
    created_by    varchar(255) null,
    modified_by   varchar(255) null
);

alter table users
    add constraint fk_users_roles foreign key (role_id) references roles (id);

create table if not exists categories
(
    id            bigint       not null primary key auto_increment,
    name          varchar(255) not null,
    code          varchar(255) not null,
    created_date  timestamp    null,
    modified_date timestamp    null,
    created_by    varchar(255) null,
    modified_by   varchar(255) null
);

create table if not exists news
(
    id                bigint       not null primary key auto_increment,
    title             varchar(255) not null,
    content           text         not null,
    thumbnail         varchar(255) null,
    short_description text         null,
    category_id       bigint       not null,
    created_date      timestamp    null,
    modified_date     timestamp    null,
    created_by        varchar(255) null,
    modified_by       varchar(255) null
);

alter table news
    add constraint fk_news_categories foreign key (category_id) references categories (id);

create table if not exists comments
(
    id            bigint       not null primary key auto_increment,
    content       text         not null,
    user_id       bigint       not null,
    news_id       bigint       not null,
    created_date  timestamp    null,
    modified_date timestamp    null,
    created_by    varchar(255) null,
    modified_by   varchar(255) null
);

alter table comments
    add constraint fk_comments_users foreign key (user_id) references users (id);
alter table comments
    add constraint fk_comments_news foreign key (news_id) references news (id);