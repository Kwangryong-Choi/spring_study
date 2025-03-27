create table macbook_class(
	midx int unsigned auto_increment,	/* 자동 증가값 */
	class_part varchar(100) not null,	/* 과정구분 */
	class_cate varchar(30) not null,	/* 과정 카테고리 */
	class_name varchar(200) not null,	/* 과정명 */
	class_day int not null default '1',	/* 학습일수 */
	class_price int not null,			/* 정가금액 */
	class_sales int not null,			/* 수강료(할인금액) */
	class_info text null,				/* 과정소개 */
	class_teacher varchar(100) null,	/* 강사명 */
	class_object text null,				/* 학습목표 */
	class_use enum('Y','N') not null default 'N',		/* 과정오픈 유/무 */
	today timestamp not null default current_timestamp,	/* 생성일자 */
	primary key(midx)
);

select * from macbook_class;

insert into macbook_class (midx,class_part,class_cate,class_name,class_day,class_price,class_sales,class_info,class_teacher,class_object,class_use,today) 
values ('0','fds','fds','fds',165156,1216,1565,'fds','fds','fds','N',now());

create table macbook_member(
	midx int unsigned auto_increment,
	mid varchar(100) not null,
	mname varchar(100) not null,
	memail varchar(100) not null,
	primary key(midx),
	unique (mid)
);

insert into macbook_member value ('0','hong','홍길동','hong@nate.com');
insert into macbook_member value ('0','gildong','홍길동','gil@naver.com');
insert into macbook_member value ('0','kim','김유신','kim@gmail.com');
insert into macbook_member value ('0','han','한석봉','han@google.com');




create table macbook_cms(
	cidx int unsigned auto_increment,
	csubject varchar(100) not null,
	cuser char(50) not null,
	cate set('cms1','cms2','cms3','cms4','cms5') not null,
	cdate timestamp not null default current_timestamp,
	primary key(cidx)
);

insert into macbook_cms values ('0','테스트','홍길동','cms2,cms5',now());
select * from macbook_cms;


create table macbook_banner(
	bidx int unsigned auto_increment,
	bname varchar(100) not null,
	file_ori text null,
	file_new text null,
	file_url text null,
	bdate timestamp not null default current_timestamp,
	primary key(bidx)
)

select * from macbook_banner;