create table book (

 b_id          number primary key,
 b_title       varchar2(200 char) not null,
 b_author      varchar2(100 char),
 b_genre       varchar2(30 char),
b_description varchar2(1000 char),
  b_quote       varchar2(500 char),
  b_image       varchar2(300),
  b_content     clob


);

create sequence book_seq;

insert into book values (book_seq.nextval, '운수 좋은 날', '현진건', '소설', '인력거꾼 김첨지는 앓는 아내를 집에 두고 비 오는 아침 일을 나선다. 이날따라 손님이 끊이지 않아 오랜만에 큰돈을 번 그는 아내에게 설렁탕을 사 줄 수 있다는 기쁨에 들뜨지만, 나가지 말라던 아내의 얼굴이 떠올라 불안도 커진다. 집에 가까워질수록 걸음은 무거워지고, 그는 두려움을 잊으려 친구와 술을 마시며 호기를 부린다. 설렁탕을 사 들고 돌아온 집에서 김첨지가 마주한 것은 아내의 죽음이다. 가장 운 좋았던 하루가 가장 큰 불행으로 끝나는 반전으로, 가난한 하층민의 비극을 그린 현진건의 대표 단편이다.', '설렁탕을 사다 놓았는데 왜 먹지를 못하니, 왜 먹지를 못하니…… 괴상하게도 오늘은! 운수가, 좋더니만…….', )

