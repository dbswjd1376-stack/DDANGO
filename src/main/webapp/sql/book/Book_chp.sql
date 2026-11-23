create table book_chapter (
    bc_id number primary key,
    b_id number,
    bc_number number,
    bc_title varchar2(200),
    bc_description varchar2(500),

    CONSTRAINT FK_BC_BOOK
    FOREIGN KEY (b_id)
    REFERENCES BOOK(b_id)

);

CREATE SEQUENCE book_chapter_seq;
