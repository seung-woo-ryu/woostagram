DROP TABLE users IF EXISTS CASCADE;
DROP TABLE posts IF EXISTS CASCADE;
DROP TABLE comments IF EXISTS CASCADE;
DROP TABLE likes IF EXISTS CASCADE;
DROP TABLE follows IF EXISTS CASCADE;

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
    foreign key (author_id) references users(id) on update cascade on delete cascade
);

create table comments (
    id bigint generated by default as identity,
    author_id bigint not null,
    post_id bigint not null,
    contents varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id),
    foreign key (author_id) references users(id) on update cascade on delete cascade,
    foreign key (post_id) references posts(id) on update cascade on delete cascade
);

create table follows (
    id bigint generated by default as identity,
    from_id bigint not null,
    to_id bigint not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id),
    foreign key (from_id) references users(id) on update cascade on delete cascade,
    foreign key (to_id) references users(id) on update cascade on delete cascade
);

create table likes (
    id bigint generated by default as identity,
    author_id bigint not null,
    post_id bigint not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id),
    foreign key (author_id) references users(id) on update cascade on delete cascade,
    foreign key (post_id) references posts(id) on update cascade on delete cascade
);

create table tags (
    id bigint generated by default as identity,
    name varchar(20) not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);

create table hashtags (
    id bigint generated by default as identity,
    post_id bigint not null,
    tag_id bigint not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id),
    foreign key (post_id) references posts(id) on update cascade on delete cascade,
    foreign key (tag_id) references tags(id) on update cascade on delete cascade
);

ALTER TABLE follows ADD UNIQUE (from_id,to_id);
ALTER TABLE likes ADD UNIQUE (author_id,post_id);
ALTER TABLE tags ADD UNIQUE (name);

