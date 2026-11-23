create table book_playlist1 (
    bp_id number primary key,
    b_id number,
    p_id number,

    CONSTRAINT FK_BP_BOOK
    FOREIGN KEY (b_id)
    REFERENCES BOOK(b_id),

    CONSTRAINT FK_BP_PLAYLIST
    FOREIGN KEY (p_id)
    REFERENCES PLAYLIST(p_id)

);




create sequence book_playlist_seq;

select * from book_playlist1;