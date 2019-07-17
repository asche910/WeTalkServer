create table article
(
    id int not null,
    authorId int null,
    title varchar(50) null,
    brief varchar(1000) null,
    content text null,
    time varchar(30) null,
    imgUrl varchar(200) null,
    videoUrl varchar(200) null,
    likeNum int null,
    commentNum int null,
    constraint article_pk
        primary key (id)
);

create table comment
(
    id int not null,
    parentId int null,
    bodyId int null,
    authorId int null,
    type int null,
    content varchar(500) null,
    time varchar(30) null,
    likeNum int null,
    constraint comment_pk
        primary key (id)
);


create table happen
(
    id int not null,
    authorId int null,
    content varchar(500) null,
    time varchar(30) null,
    likeNum int null,
    commentNum int null,
    constraint happen_pk
        primary key (id)
);

create table requirement
(
    id int not null,
    type int null,
    authorId int null,
    title varchar(50) null,
    brief varchar(500) null,
    content text null,
    time varchar(30) null,
    imgUrl varchar(200) null,
    videoUrl varchar(200) null,
    likeNum int null,
    commentNum int null,
    constraint requirement_pk
        primary key (id)
);

create table topic
(
    id int not null,
    authorId int null,
    title varchar(50) null,
    content varchar(500) null,
    coverUrl varchar(200) null,
    time varchar(30) null,
    followerNum int null,
    replyNum int null,
    constraint topic_pk
        primary key (id)
);

create table topicReply
(
    id int not null,
    topicId int null,
    authorId int null,
    content text null,
    time varchar(30) null,
    imgUrl varchar(200) null,
    videoUrl varchar(200) null,
    likeNum int null,
    commentNum int null,
    constraint topicReply_pk
        primary key (id)
);

create table user
(
    id int not null,
    username varchar(20) null,
    password varchar(100) null,
    nickname varchar(20) null,
    gender varchar(2) null,
    imgAvatar varchar(200) null,
    imgBg varchar(200) null,
    signature varchar(200) null,
    tel varchar(12) null,
    email varchar(30) null,
    address varchar(50) null,
    profession varchar(30) null,
    school varchar(30) null,
    description varchar(200) null,
    isExpert boolean null,
    isVip boolean null,
    followNum int null,
    followerNum int null,
    constraint user_pk
        primary key (id)
);

create unique index user_username_uindex
    on user (username);

