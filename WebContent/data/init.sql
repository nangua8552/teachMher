teacher(老师)
-id
-name
-age
-sex
-level(职称)

create table teacher(
    id number primary key,
    name varchar2(50),
    age number,
    sex  char(1),  --1 boy 0girl
    lev varchar2(50)
);


classe(班级)
-id  
-name
-college(学院)
-tid(外键  唯一约束 -->一对一)

create table classe(
        id number primary key,
        name varchar2(50),
        college varchar2(100),
        tid number unique,
        constraint classe_tid_fk foreign key (tid) references teacher(id)
);

student(学生)
-sno    snoxxxxx    sno00001    sno00002  "自动生成"-->触发器
-name
-age
-sex
-tel
-address
-cid(外键--一对多)
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

course(课程)
-id
-name
-credit(学分)
create table course(
       id number primary key,
       name varchar2(50),
       credit number
);

c_s(联合主键)
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



