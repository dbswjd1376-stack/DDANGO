create table music_emotion(
    me_id number(30) primary key,
    m_id number(30) not null,
    e_id number(30) not null
);

create sequence music_emotion_seq;