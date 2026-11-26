create table PLAYLIST(
    p_id number(20) primary key,
    p_img varchar2(300 char) not null,
    p_name varchar2(30 char) not null,
    p_description varchar2 (100 char) not null,
    m_type number(20) not null ,
    p_date date default sysdate,
    u_id not null,
    constraint fk_playlist_user foreign key (u_id) references USERS(u_id)

);

create sequence playlist_seq;

insert into PLAYLIST values (playlist_seq.nextval, 'a123', '집 가고싶을 때 듣는 플리', '퇴근이.. 퇴근이 하고싶어요', '1', sysdate, 'sm123');
insert into PLAYLIST values (playlist_seq.nextval, 'a1234', '집 가기 싫을 때 듣는 플리', '퇴근이.. 퇴근이 하고싶어요', '2', sysdate, 'sm1234');
insert into PLAYLIST values (playlist_seq.nextval, 'a1235', ' 플리', '퇴근이.. 퇴근이 하고싶어요', '2', sysdate, 'sm12345');

select * from PLAYLIST;



