create table music (
    m_id number(30) primary key,
    m_title varchar2(200 char) not null,
    m_artist varchar2(100 char)not null,
    m_genre varchar2(50 char)not null,
    m_album_image varchar2(300 char)not null,
    m_youtubeu_url varchar2(300 char)not null,
    m_spotyfy_url varchar2(300 char)not null,
    m_itunes_url varchar2(300 char)not null
);

create sequence music_seq;

