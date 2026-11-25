create table book_emotion (

    be_id number(30) primary key,
    book_title varchar2(200 char) not null,
    e_id number(30) not null

--     constraint fk_book_emotion_emotion foreign key (e_id) references emotion(e_id)

);

create sequence book_emotion_seq;