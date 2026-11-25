create table book_playlist1 (
    bp_id number primary key,
    b_id number not null ,
    p_id number not null
);

create sequence book_playlist_seq1;

select * from book_playlist1;