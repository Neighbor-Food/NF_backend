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

-- 샘플 음식점 데이터
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('한식집1', '한식', '3000', '20000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('한식집2', '한식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('분식집1', '분식', '2000', '15000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('분식집2', '분식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('중국집1', '중식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('중국집2', '중식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('일식집1', '일식', '3000', '20000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('일식집2', '일식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('양식집1', '양식', '3000', '20000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('양식집2', '양식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('스타벅스1', '카페', '3000', '20000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('스타벅스2', '카페', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('야식가게1', '야식', '3000', '20000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('야식가게2', '야식', '3000', '10000');

insert into menu (name, price, restaurant_no)
values ('김밥', '2500', 1);
insert into menu (name, price, restaurant_no)
values ('김밥', '2500', 2);
insert into menu (name, price, restaurant_no)
values ('라면', '3000', 1);
insert into menu (name, price, restaurant_no)
values ('라면', '3000', 2);
insert into menu (name, price, restaurant_no)
values ('떡볶이', '4000', 1);
insert into menu (name, price, restaurant_no)
values ('떡볶이', '4000', 2);
insert into menu (name, price, restaurant_no)
values ('김말이', '3500', 1);
insert into menu (name, price, restaurant_no)
values ('김말이', '3500', 2);
insert into menu (name, price, restaurant_no)
values ('순대', '4000', 1);
insert into menu (name, price, restaurant_no)
values ('순대', '4000', 2);
insert into menu (name, price, restaurant_no)
values ('오징어튀김', '4000', 1);
insert into menu (name, price, restaurant_no)
values ('오징어튀김', '4000', 2);
insert into menu (name, price, restaurant_no)
values ('새우튀김', '4000', 1);
insert into menu (name, price, restaurant_no)
values ('새우튀김', '4000', 2);
insert into menu (name, price, restaurant_no)
values ('어묵튀김', '2000', 1);
insert into menu (name, price, restaurant_no)
values ('어묵튀김', '2000', 2);
insert into menu (name, price, restaurant_no)
values ('삼각만두', '2000', 1);
insert into menu (name, price, restaurant_no)
values ('삼각만두', '2000', 2);
insert into menu (name, price, restaurant_no)
values ('쿨피스', '1500', 1);
insert into menu (name, price, restaurant_no)
values ('쿨피스', '1500', 2);


insert into menu (name, price, restaurant_no)
values ('광어초밥', '15000', 3);
insert into menu (name, price, restaurant_no)
values ('연어초밥', '18000', 3);
insert into menu (name, price, restaurant_no)
values ('소바', '6000', 3);
insert into menu (name, price, restaurant_no)
values ('우동', '6000', 3);
insert into menu (name, price, restaurant_no)
values ('연어덮밥', '6000', 3);

-- 샘플 게시글 데이터
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '한식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 1);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '한식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 1);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '분식','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 3);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '분식','2023-06-03T14:30', 5, 3,'2023-05-20T10:24:50.5831464', 1, 3);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '중식','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 5);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '중식','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 5);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '일식','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 7);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '일식','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 7);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '양식','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 9);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '양식','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 9);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '카페','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 11);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '카페','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 11);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title1', 'contents1', '야식','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 13);
insert into board (title, contents, category, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('title2', 'contents1', '야식','2023-06-03T14:30', 5, 1,'2023-05-20T10:24:50.5831464', 1, 13);

-- 샘플 참가 데이터
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
values ( '주문시각 좀만 미룰 수 있나요?', '2023-05-20T10:24:50.5831464', 4, 1  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '아직 참가는 안했습니다 미룰 수 있으면 참가할게요', '2023-05-20T10:24:50.5831464', 4, 1  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '10분 정도 미룰게요', '2023-05-20T10:24:50.5831464', 1, 1  );
insert into reply (contents, reg_date, member_no, board_no)
values ( '감사요', '2023-05-20T10:24:50.5831464', 4, 1  );
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