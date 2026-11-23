create table test (
    t_no number(10) primary key,
    t_name varchar2(10 char) not null,
    t_age number(10) not null

);

drop table test;
select * from TEST;

create sequence test_seq;

insert into test values (test_seq.nextval, 'sb1', 10);
insert into test values (test_seq.nextval, 'sb2', 20);
insert into test values (test_seq.nextval, 'sb3', 30);

select * from test;