DROP TABLE PHONE_BOOK purge;

create table PHONE_BOOK(
    id number(10) primary key,
    name varchar2(20) not null,
    hp varchar2(30) not null,
    tell varchar2(30) not null,
    trgdate date
    );
    
    create sequence SEQ_PHONE_BOOK_PK increment by 1 start with 100;
    


    insert into PHONE_BOOK values(SEQ_PHONE_BOOK_PK.nextval,'고길동','031-111-1111','010-1111-1111',sysdate);
    insert into PHONE_BOOK values(SEQ_PHONE_BOOK_PK.nextval,'둘리','02-222-2222','010-2222-2222',sysdate);
    insert into PHONE_BOOK values(SEQ_PHONE_BOOK_PK.nextval,'마이콜','032-333-3333','010-3333-3333',sysdate);
    
    
    SELECT * FROM PHONE_BOOK