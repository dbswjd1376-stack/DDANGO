create table chapter_playlist (
                                  cp_id number primary key,
                                  bc_id number not null,
                                  p_id  number not null
--                                   CONSTRAINT FK_CP_CHAPTER FOREIGN KEY (bc_id) REFERENCES BOOK_CHAPTER(bc_id) ON DELETE CASCADE,
--                                   CONSTRAINT FK_CP_PLAYLIST FOREIGN KEY (p_id) REFERENCES PLAYLIST(p_id) ON DELETE CASCADE,
--                                   CONSTRAINT UQ_CHAPTER_PLAYLIST UNIQUE (bc_id, p_id)
);
create sequence chapter_playlist_seq;


select * from CHAPTER_PLAYLIST;