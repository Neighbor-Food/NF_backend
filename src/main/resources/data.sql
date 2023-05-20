-- create table restaurant
-- (
--     restaurant_no integer not null auto_increment,
--     name            VARCHAR not null,
--     category            VARCHAR not null,
--     delivery_tip    VARCHAR not null,
--     min_order_price VARCHAR not null,
--     primary key (restaurant_no)
-- );
insert into member (email, password, name, push_email, bank, bank_account_number, email_auth)
values ('user01@kumoh.ac.kr', '1234', '김이름', '', '국민', '000-0000-000', true);
insert into member (email, password, name, push_email, bank, bank_account_number, email_auth)
values ('user02@kumoh.ac.kr', '1234', '나이름', '', '농협', '000-0000-000', true);
insert into member (email, password, name, push_email, bank, bank_account_number, email_auth)
values ('user03@kumoh.ac.kr', '1234', '박이름', '', '신한', '000-0000-000', true);
insert into member (email, password, name, push_email, bank, bank_account_number, email_auth)
values ('user04@kumoh.ac.kr', '1234', '이이름', '', '우리', '000-0000-000', true);
insert into member (email, password, name, push_email, bank, bank_account_number, email_auth)
values ('user05@kumoh.ac.kr', '1234', '최이름', '', '하나', '000-0000-000', true);

-- 샘플 게시글 데이터
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '한식','2023-06-03T14:30', 5, 3,'2023-05-20T10:24:50.5831464', 1, 1);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '한식','2023-06-03T14:30', 5, 3,'2023-05-20T10:24:50.5831464', 1, 1);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '분식','2023-06-03T14:30', 5, 3,'2023-05-20T10:24:50.5831464', 1, 3);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '분식','2023-06-03T14:30', 5, 4,'2023-05-20T10:24:50.5831464', 1, 3);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '중식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 5);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '중식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 5);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '일식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 7);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '일식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 7);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '양식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 9);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '양식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 9);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '카페','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 11);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '카페','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 11);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '야식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 13);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '야식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 13);

-- 샘플 참가 데이터
insert into participation (board_no, member_no)
values ( 1,1 );
insert into participation (board_no, member_no)
values ( 2,1 );
insert into participation (board_no, member_no)
values ( 3,1 );
insert into participation (board_no, member_no)
values ( 4,1 );
insert into participation (board_no, member_no)
values ( 5,1 );
insert into participation (board_no, member_no)
values ( 6,1 );
insert into participation (board_no, member_no)
values ( 7,1 );
insert into participation (board_no, member_no)
values ( 8,1 );
insert into participation (board_no, member_no)
values ( 9,1 );
insert into participation (board_no, member_no)
values ( 10,1 );
insert into participation (board_no, member_no)
values ( 11,1 );
insert into participation (board_no, member_no)
values ( 12,1 );
insert into participation (board_no, member_no)
values ( 13,1 );
insert into participation (board_no, member_no)
values ( 14,1 );

insert into participation (board_no, member_no)
values ( 1,2 );
insert into participation (board_no, member_no)
values ( 1,3 );
insert into participation (board_no, member_no)
values ( 2,2 );
insert into participation (board_no, member_no)
values ( 2,4 );
insert into participation (board_no, member_no)
values ( 3,3 );
insert into participation (board_no, member_no)
values ( 3,4 );
insert into participation (board_no, member_no)
values ( 4,2 );
insert into participation (board_no, member_no)
values ( 4,3 );
insert into participation (board_no, member_no)
values ( 4,4 );
insert into participation (board_no, member_no)
values ( 5,2 );
insert into participation (board_no, member_no)
values ( 6,2 );
insert into participation (board_no, member_no)
values ( 7,2 );
insert into participation (board_no, member_no)
values ( 8,2 );
insert into participation (board_no, member_no)
values ( 9,2 );
insert into participation (board_no, member_no)
values ( 10,2 );
insert into participation (board_no, member_no)
values ( 11,2 );
insert into participation (board_no, member_no)
values ( 12,2 );
insert into participation (board_no, member_no)
values ( 13,2 );
insert into participation (board_no, member_no)
values ( 14,2 );

-- 샘플 댓글 데이터
insert into reply (contents, reg_date, member_no, board_no)
values ( '주문시각 좀만 미룰 수 있나요?', '2023-05-20T10:24:50.5831464', 2, 1  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '전화번호 010-0000-0000 입니다. 죄송합니다.', '2023-05-20T10:24:50.5831464', 2, 1  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '10분 정도 미룰게요', '2023-05-20T10:24:50.5831464', 1, 1  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '감사합니다.', '2023-05-20T10:24:50.5831464', 2, 1  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 2  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 3  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 4  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 5  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 6  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 7  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 8  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 9  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 10  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 11 );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 12 );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 13 );
insert into reply (contents, reg_date, member_no, board_no)
values ( '샘플 댓글 데이터', '2023-05-20T10:24:50.5831464', 4, 14 );