
    create table answer (
        answer_id bigint not null auto_increment,
        created_date datetime,
        question bigint,
        writer varchar(255),
        primary key (answer_id)
    );

    create table answer_content_holder (
        answer_id bigint not null unique,
        contents longtext not null
    );

    create table question (
        question_id bigint not null auto_increment,
        created_date datetime not null,
        title varchar(100) not null,
        writer varchar(255),
        primary key (question_id)
    );

    create table question_content_holder (
        question_id bigint not null unique,
        contents longtext not null
    );

    create table question_tag (
        question_id bigint not null,
        tag_id bigint not null,
        primary key (question_id, tag_id)
    );

    create table tag (
        tag_id bigint not null auto_increment,
        name varchar(50) not null unique,
        pooled bit not null,
        tagged_count integer not null,
        primary key (tag_id)
    );

    create table user (
        user_id varchar(255) not null,
        email varchar(255),
        is_admin bit not null,
        name varchar(255),
        password varchar(255),
        primary key (user_id)
    );

    alter table answer 
        add index fk_answer_writer (writer), 
        add constraint fk_answer_writer 
        foreign key (writer) 
        references user (user_id);

    alter table answer 
        add index fk_answer_parent_id (question), 
        add constraint fk_answer_parent_id 
        foreign key (question) 
        references question (question_id);

    alter table answer_content_holder 
        add index fk_answer_content_holder_answer_id (answer_id), 
        add constraint fk_answer_content_holder_answer_id 
        foreign key (answer_id) 
        references answer (answer_id);

    alter table question 
        add index fk_question_writer (writer), 
        add constraint fk_question_writer 
        foreign key (writer) 
        references user (user_id);

    alter table question_content_holder 
        add index fk_question_content_holder_question_id (question_id), 
        add constraint fk_question_content_holder_question_id 
        foreign key (question_id) 
        references question (question_id);

    alter table question_tag 
        add index fk_question_tag_question_id (question_id), 
        add constraint fk_question_tag_question_id 
        foreign key (question_id) 
        references question (question_id);

    alter table question_tag 
        add index fk_question_tag_tag_id (tag_id), 
        add constraint fk_question_tag_tag_id 
        foreign key (tag_id) 
        references tag (tag_id);
