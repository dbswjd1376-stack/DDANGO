create table PLAYLIST_BOOK(
    bp_id  number (20) primary key,
    b_id number (20) not null,
    p_id number(20) not null
);

create sequence playlist_book_seq;

insert into PLAYLIST_BOOK values (playlist_book_seq.nextval, 1,1 );
insert into PLAYLIST_BOOK values (playlist_book_seq.nextval, 2,2 );
insert into PLAYLIST_BOOK values (playlist_book_seq.nextval, 3,3 );

select * from PLAYLIST_BOOK;
