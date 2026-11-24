create table book_playlist1 (
    bp_id number primary key,
    b_id number not null ,
    p_id number not null,

    CONSTRAINT FK_BP_BOOK FOREIGN KEY (b_id)
        REFERENCES BOOK(b_id) ON DELETE CASCADE,

    CONSTRAINT FK_BP_PLAYLIST FOREIGN KEY (p_id)
    REFERENCES PLAYLIST(p_id) ON DELETE CASCADE,

    CONSTRAINT UQ_BOOK_PLAYLIST UNIQUE (b_id, p_id)
);

create sequence book_playlist_seq1;

select * from book_playlist1;