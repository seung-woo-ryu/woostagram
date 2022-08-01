insert into users (comment,email,name,nickname,password,profile_url) values ('comment a','tmddn645@naver.com','seungwooryu','seungwooryu','vvee12','profile_url_infoa');
insert into users (comment,email,name,nickname,password,profile_url) values ('comment b','seungbin645@naver.com','seungbin','seungbin','vvee12','profile_url_infob');
insert into users (comment,email,name,nickname,password,profile_url) values ('comment c','tagun645@naver.com','kimtagun','kimtagun','vvee12','profile_url_infoc');
insert into users (comment,email,name,nickname,password,profile_url) values ('comment d','younggi645@naver.com','kimyounggi','kimyounggi','vvee12','profile_url_infod');

insert into posts(contents,image_url,author_id) values('contentsa','img_urea',1);
insert into posts(contents,image_url,author_id) values('contentsb','img_urlb',2);
insert into posts(contents,image_url,author_id) values('contentsc','img_urlc',3);

insert into likes(post_id,author_id) values(1,1);

insert into comments(contents,post_id,author_id) values('comments',1,1);