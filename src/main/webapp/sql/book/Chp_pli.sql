create table chapter_playlist (
                                  cp_id number primary key,
                                  bc_id number not null,
                                  p_id  number not null

);
create sequence chapter_playlist_seq;


select * from CHAPTER_PLAYLIST;