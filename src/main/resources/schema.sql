DROP TABLE users IF EXISTS;
DROP TABLE posts IF EXISTS;
DROP TABLE comments IF EXISTS;
DROP TABLE likes IF EXISTS;

create table users (
    id bigint generated by default as identity,
    created_at timestamp,
    updated_at timestamp,
    email varchar(255) not null,
    name varchar(255) not null,
    nickname varchar(255) not null,
    password varchar(255) not null,
    comment varchar(255) not null,
    profile_url varchar(255) not null,
    primary key (id)
);

create table posts (
    id bigint generated by default as identity,
    created_at timestamp,
    updated_at timestamp,
    contents varchar(255) not null,
    image_url varchar(255) not null,
    author_id bigint not null,
    primary key (id),
    foreign key (author_id) references users(id) on update cascade
);

create table comments (
    id bigint generated by default as identity,
    author_id bigint not null,
    post_id bigint not null,
    contents varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id),
    foreign key (author_id) references users(id) on update cascade,
    foreign key (post_id) references posts(id) on update cascade
);

create table likes (
    id bigint generated by default as identity,
    author_id bigint not null,
    post_id bigint not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id),
    foreign key (author_id) references users(id) on update cascade,
    foreign key (post_id) references posts(id) on update cascade
);
