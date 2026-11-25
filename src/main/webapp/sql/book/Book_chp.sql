create table book_chapter (
    bc_id number primary key,
    b_id number not null,
    bc_number number not null,
    bc_title varchar2(200 char),
    bc_description varchar2(500 char),

--     CONSTRAINT FK_BC_BOOK FOREIGN KEY (b_id)
--         REFERENCES BOOK(b_id) ON DELETE CASCADE,  --책을 지우면 챕터도 같이 지움--
--     CONSTRAINT UQ_CHAPTER UNIQUE (b_id, bc_number)

);

CREATE SEQUENCE book_chapter_seq;

select * from BOOK_CHAPTER;


insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '변신'),
        1, '제1장');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '변신'),
        2, '제2장');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '변신'),
        3, '제3장');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        1, '제1장 문 이야기');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        2, '제2장 하이드 씨를 찾아서');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        3, '제3장 지킬 박사는 아주 태연했다');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        4, '제4장 커루 살인 사건');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        5, '제5장 편지 사건');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        6, '제6장 래니언 박사 사건');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        7, '제7장 창가에서 생긴 일');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        8, '제8장 마지막 밤');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        9, '제9장 래니언 박사의 진술');

insert into book_chapter (bc_id, b_id, bc_number, bc_title)
values (book_chapter_seq.nextval,
        (select b_id from book where b_title = '지킬 박사와 하이드 씨'),
        10, '제10장 헨리 지킬의 사건 전말 진술');


select b.b_title, c.bc_number
from book_chapter c join book b on b.b_id = c.b_id
order by b.b_title, c.bc_number;

select b_id, b_title
from book
where b_title like '%지킬%';

select *
from book_chapter
where b_id = (
    select b_id
    from book
    where b_title like '%지킬%'
);

select *
from book_chapter
order by bc_id desc;