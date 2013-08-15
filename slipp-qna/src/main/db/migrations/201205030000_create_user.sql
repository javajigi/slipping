    drop table user if exists;
   
    create table user (
        user_id varchar(255) not null,
        email varchar(255),
        is_admin boolean not null,
        name varchar(255),
        password varchar(255),
        primary key (user_id)
    );