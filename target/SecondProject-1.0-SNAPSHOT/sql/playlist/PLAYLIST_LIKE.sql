create table PLAYLIST_LIKE(
    pl_id number (10) primary key,
    p_id number (10) not null,
    u_id varchar2 (30 char) not null
);

create sequence playlist_like_seq;

insert into PLAYLIST_LIKE values (playlist_like_seq.nextval, 1, 'yj');
insert into PLAYLIST_LIKE values (playlist_like_seq.nextval, 2, 'yj');
insert into PLAYLIST_LIKE values (playlist_like_seq.nextval, 3, 'yj');

select * from PLAYLIST_LIKE;