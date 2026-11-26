create table PLAYLIST_MUSIC(
    pm_id number (20) primary key ,
    p_id number(20) not null,
    m_id number(20) not null
);

create sequence playlist_music_seq;

insert into PLAYLIST_MUSIC values (playlist_music_seq.nextval, 1, 30);
insert into PLAYLIST_MUSIC values (playlist_music_seq.nextval, 2, 30);
insert into PLAYLIST_MUSIC values (playlist_music_seq.nextval, 3, 30);