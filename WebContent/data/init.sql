teacher(��ʦ)
-id
-name
-age
-sex
-level(ְ��)

create table teacher(
    id number primary key,
    name varchar2(50),
    age number,
    sex  char(1),  --1 boy 0girl
    lev varchar2(50)
);


classe(�༶)
-id  
-name
-college(ѧԺ)
-tid(���  ΨһԼ�� -->һ��һ)

create table classe(
        id number primary key,
        name varchar2(50),
        college varchar2(100),
        tid number unique,
        constraint classe_tid_fk foreign key (tid) references teacher(id)
);

student(ѧ��)
-sno    snoxxxxx    sno00001    sno00002  "�Զ�����"-->������
-name
-age
-sex
-tel
-address
-cid(���--һ�Զ�)
create table student(
       sno varchar2(50) primary key,
       name varchar2(50),
       age number(2),
       sex char(1),
       tel varchar2(50),
       address varchar2(100),
       cid number,
       constraint student_cid_fk foreign key (cid) references classe(id)
       
);

course(�γ�)
-id
-name
-credit(ѧ��)
create table course(
       id number primary key,
       name varchar2(50),
       credit number
);

c_s(��������)
-sid
-cid
create table c_s(
   sid varchar2(50),
   cid number,
   constraint  c_s_pk primary key (sid,cid),
   constraint c_s_sid_fk foreign key (sid) references student(sno),
   constraint c_s_cid_fk foreign key (cid) references course(id)
        
);

c_t
-tid
-cid
create table c_t(  
   cid number,
   tid number,
   constraint  c_t_pk primary key (cid,tid),
   constraint c_t_cid_fk foreign key (cid) references course(id),
   constraint c_t_tid_fk foreign key (tid) references teacher(id)
        
);



create table customer(
       id number primary key,
       account varchar2(50),
       password varchar2(100),
       name varchar2(50)
);



