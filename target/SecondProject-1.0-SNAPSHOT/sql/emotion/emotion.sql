create table emotion (
    e_id number(30) primary key,
    e_name varchar2(50 char) not null,
    e_parent_id varchar2(50 char)not null
);

create sequence emotion_seq;

insert into emotion values (emotion_seq.nextval, '기쁨', '행복');
