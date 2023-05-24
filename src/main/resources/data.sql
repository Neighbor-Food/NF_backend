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

--식당 데이터
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (1, '제주집', '한식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (2, '하루', '일식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (3, '선산곱창', '야식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (4, '구미가당김', '카페', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (5, '와촌식육식당 옥계점', '한식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (6, '제주집', '한식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (7, '이오카츠 구미옥계점', '일식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (8, '라쿵푸마라탕 구미옥계5호점', '중식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (9, '스위시 옥계점', '일식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (10, '서민찜닭 옥계점', '한식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (11, '초심', '일식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (12, '신대광반점', '중식', 0.0, 0.0);
INSERT INTO restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (13, '레브', '양식', 0.0, 0.0);

insert into restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (14, '긴급분식', '분식', 3000, 10000);
insert into restaurant (restaurant_no, name, category, delivery_tip, min_order_price) VALUES (15, '긴급분식 2호점', '분식', 2000, 13000);

--메뉴데이터
USE uracle_kit;
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (1, '제주오겹살 (150g)', '1', 11900.0, "2023-05-08 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (2, '제주목살 (150g)', '1', 11900.0, "2023-05-08 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (3, '신국물갈비 (250g)', '1', 13900.0, "2023-05-08 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (4, '김치말이국수', '1', 4000.0, "2023-05-08 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (5, '활어회(소)', '2', 60000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (6, '활어회(중)', '2', 80000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (7, '활어회(대)', '2', 100000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (8, '활어회(특대)', '2', 120000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (9, '특물회(점심메뉴)', '2', 20000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (10, '하루정식(점심메뉴)', '2', 20000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (11, '특초밥(점심메뉴)', '2', 17000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (12, '우럭탕1인(점심메뉴)', '2', 20000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (13, '도다리(소)', '2', 70000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (14, '게르치(소)', '2', 90000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (15, '줄돔(소)', '2', 70000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (16, '참돔(소)', '2', 70000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (17, '농어(소)', '2', 70000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (18, '모둠해산물', '2', 70000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (19, '물회(점심메뉴)', '2', 15000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (20, '간장게장정식(점심메뉴)', '2', 20000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (21, '회덮밥(점심메뉴)', '2', 12000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (22, '초밥10개(점심메뉴)', '2', 12000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (23, '전복회', '2', 25000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (24, '새우튀김', '2', 15000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (25, '곱창전골 (1인분)', '3', 8000.0, "2023-05-16 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (26, '수비드스테이크', '4', 30000.0, "2023-04-19 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (27, '감베로니크림파스타', '4', 15000.0, "2023-04-19 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (28, '감베로니오일파스타', '4', 14000.0, "2023-04-19 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (29, '관자스테이크', '4', 22000.0, "2023-04-19 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (30, '토마토볼로네제', '4', 14000.0, "2023-04-19 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (31, '샌드위치', '4', 11000.0, "2023-04-19 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (32, '와촌돼지찌개', '5', 9000.0, "2023-05-11 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (33, '와촌삼겹살(150g) 2인분', '5', 10000.0, "2023-05-11 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (34, '와촌불고기(300g)', '5', 13000.0, "2023-05-11 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (35, '와촌치즈불고기(300g)', '5', 14000.0, "2023-05-11 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (36, '제주오겹살 (150g)', '6', 11900.0, "2023-05-08 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (37, '제주목살 (150g)', '6', 11900.0, "2023-05-08 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (38, '신국물갈비 (250g)', '6', 13900.0, "2023-05-08 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (39, '김치말이국수', '6', 4000.0, "2023-05-08 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (40, '베이직 돈까스', '7', 9500.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (41, '치즈돈까스', '7', 9900.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (42, '고구마돈까스', '7', 9900.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (43, '철판치즈돈까스', '7', 12900.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (44, '치즈떡볶이 돈까스', '7', 9500.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (45, '마라탕 (100g)', '8', 1600.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (46, '마라샹궈 (100g)', '8', 3000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (47, '마라빤 (100g)', '8', 3000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (48, '마라훈둔', '8', 8000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (49, '샤오룽보', '8', 8000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (50, '스위시 모둠 도시락', '9', 13500.0, "2023-01-18 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (51, '커플팩', '9', 25000.0, "2023-01-18 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (52, '서민찜닭 (소)', '10', 16000.0, "2022-11-11 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (53, '묵은지찜닭 (소)', '10', 20000.0, "2022-11-11 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (54, '서민순살찜닭 (소)', '10', 18000.0, "2022-11-11 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (55, '묵은지순살찜닭 (소)', '10', 22000.0, "2022-11-11 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (56, '옛날제육볶음 (소)', '10', 19000.0, "2022-11-11 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (57, '모듬 사시미 (소)', '11', 20000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (58, '광어초밥 (1ps)', '11', 1800.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (59, '광어지느러미초밥 (1ps)', '11', 2000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (60, '밀치초밥 (1ps)', '11', 1500.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (61, '밀치뱃살초밥 (1ps)', '11', 2000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (62, '연어초밥 (1ps)', '11', 1500.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (63, '짜장면', '12', 5000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (64, '짬뽕', '12', 6000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (65, '볶음밥', '12', 6000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (66, '중화비빔밥', '12', 8000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (67, '탕수육(소)', '12', 18000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (68, '문어', '13', 23000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (69, '감자튀김', '13', 8000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (70, '가지', '13', 14000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (71, '한우 채끝', '13', 61000.0, "2022-11-12 00:00:00");
INSERT INTO menu (menu_no, name, restaurant_no, price, last_update) VALUES (72, '커리파스타', '13', 22000.0, "2022-11-12 00:00:00");

insert into menu (menu_no, name, restaurant_no, price, last_update) VALUES (73, '로제떡볶이', '14', 15000.0, "2022-11-12 00:00:00");
insert into menu (menu_no, name, restaurant_no, price, last_update) VALUES (74, '떡볶이', '14', 4000.0, "2022-11-12 00:00:00");
insert into menu (menu_no, name, restaurant_no, price, last_update) VALUES (75, '떡볶이', '15', 4000.0, "2022-11-12 00:00:00");
insert into menu (menu_no, name, restaurant_no, price, last_update) VALUES (76, '삼각만두', '15', 4000.0, "2022-11-12 00:00:00");



-- 샘플 게시글 데이터
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('고기 먹을 사람 구해요', '고기 먹을 사람 구합니다 같이 배달 ㄱㄱ', '한식', '예시주소','2023-06-03T14:30', 5, 3,'2023-05-20T10:24:50.5831464', 1, 1);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('고기 먹을 사람 구해요@@@', '삼겹살 한 입 념념 공깃밥까지 야무지다... 같이 배달할 사람?', '한식', '예시주소','2023-06-03T14:30', 5, 3,'2023-05-20T10:24:50.5831464', 1, 5);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('긴급분식 머임', '새로생긴것같은데배달시킬사람', '분식', '예시주소','2023-06-03T14:30', 5, 3,'2023-05-20T10:24:50.5831464', 1, 14);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('긴급분식 2호점도 있네', '여기가 더 좋아보임 떡볶이 먹을사람 참여 ㄱ', '분식', '예시주소','2023-06-03T14:30', 5, 4,'2023-05-20T10:24:50.5831464', 1, 15);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('마라탕 땡김', '오랜만에 마라탕 먹고싶당 소고기 추가해서...', '중식', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 8);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('중식 먹을 사람?', '짜장 짬뽕 땡기지 않나요 땡기면 드루와', '중식', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 12);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('초밥 가즈아', '연어초밥이 먹고싶어요. 초밥 먹을사람 구함. 초밥 안땡기나요? 배달비 더치페이 해요 제발', '일식', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 11);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('초밥 파티원 구함', '아무나 ㄱ', '일식', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 2);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('파스타 먹을 사람?', '파스타 땡기지 않나요', '양식', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 13);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('파스타 먹을 사람~~', '이 글을 본 당신은 파스타가 먹고싶을 것', '양식', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 13);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('카페 같이 배달 주문할 사람', '배달비 더치페이 ㄱㄱ', '카페', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 4);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('식후에 커피 한 잔 어떰', '아이스 아메리카노 하나면 속이 싸악 내려감', '카페', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 4);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('막창이..먹고싶읍니다', '막창이... 먹고싶다.. 눈 앞에 아른아른 거린다...', '야식', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 3);
insert into board (title, contents, category, location, order_time, max_people, cur_people, reg_date, member_no, restaurant_no)
values ('곱창 먹을 사람 급구', '유튜브 보다가 곱창 땡겨서 파티원 구해봄', '야식', '예시주소','2023-06-03T14:30', 5, 2,'2023-05-20T10:24:50.5831464', 1, 3);

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

insert into basket (quantity, board_no, member_no, menu_no, confirmed)
values ( 3, 1, 1, 1, 0 );
insert into basket (quantity, board_no, member_no, menu_no, confirmed)
values ( 4, 1, 2, 2, 0 );
insert into basket (quantity, board_no, member_no, menu_no, confirmed)
values ( 2, 1, 3, 4, 0 );
insert into basket (quantity, board_no, member_no, menu_no, confirmed)
values ( 3, 2, 1, 1, 0 );