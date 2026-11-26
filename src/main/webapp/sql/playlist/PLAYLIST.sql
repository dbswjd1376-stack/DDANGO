create table PLAYLIST(
    p_id number(20) primary key,
    p_img varchar2(300 char) not null,
    p_name varchar2(30 char) not null,
    p_description varchar2 (100 char) not null,
    p_date date not null,
    u_id number not null

);

create sequence playlist_seq;

insert into PLAYLIST values (playlist_seq.nextval, 'a123', '집 가고싶을 때 듣는 플리', '퇴근이.. 퇴근이 하고싶어요', sysdate, 1);
insert into PLAYLIST values (playlist_seq.nextval, 'a1234', '집 가기 싫을 때 듣는 플리', '퇴근이.. 퇴근이 하고싶어요', sysdate, 2);
insert into PLAYLIST values (playlist_seq.nextval, 'a1235', ' 플리', '퇴근이.. 퇴근이 하고싶어요', sysdate, 3);

select * from PLAYLIST;
