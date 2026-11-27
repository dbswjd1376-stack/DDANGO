CREATE TABLE USERS (
                       u_id NUMBER PRIMARY KEY,
                       u_login_id VARCHAR2(30 CHAR) NOT NULL UNIQUE,
                       u_password VARCHAR2(255 CHAR) NOT NULL,
                       u_nickname VARCHAR2(50 CHAR) NOT NULL,
                       u_email VARCHAR2(100 CHAR) NOT NULL,
                       u_created_at DATE DEFAULT SYSDATE
);



-- 회원번호 자동 생성 시퀀스

CREATE SEQUENCE USERS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;


INSERT INTO USERS (u_id, u_login_id, u_password, u_nickname, u_email) VALUES (USERS_SEQ.NEXTVAL, 'yj', 'yj1004', 'yj', 'yj@test.com');
INSERT INTO USERS (u_id, u_login_id, u_password, u_nickname, u_email) VALUES (USERS_SEQ.NEXTVAL, 'sb', 'sb1004', 'sb', 'sb@test.com');
INSERT INTO USERS (u_id, u_login_id, u_password, u_nickname, u_email) VALUES (USERS_SEQ.NEXTVAL, 'gy', 'gy1004', 'gy', 'gy@test.com');
INSERT INTO USERS (u_id, u_login_id, u_password, u_nickname, u_email) VALUES (USERS_SEQ.NEXTVAL, 'hk', 'hk1004', 'hk', 'hk@test.com');

SELECT * FROM USERS;
