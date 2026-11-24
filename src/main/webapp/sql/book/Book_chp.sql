create table book_chapter (
    bc_id number primary key,
    b_id number not null,
    bc_number number not null,
    bc_title varchar2(200 char),
    bc_description varchar2(500 char),
    bc_content clob,

    CONSTRAINT FK_BC_BOOK FOREIGN KEY (b_id)
        REFERENCES BOOK(b_id) ON DELETE CASCADE,  --책을 지우면 챕터도 같이 지움--
    CONSTRAINT UQ_CHAPTER UNIQUE (b_id, bc_number)

);

CREATE SEQUENCE book_chapter_seq;

insert into book_chapter (bc_id, b_id, bc_number, bc_title, bc_description)
values (book_chapter_seq.nextval, (select b_id from book where b_title = '변신'), 1, '제 1부',
        '');