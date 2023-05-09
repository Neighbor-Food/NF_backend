-- create table restaurant
-- (
--     restaurant_no integer not null auto_increment,
--     name            VARCHAR not null,
--     delivery_tip    VARCHAR not null,
--     min_order_price VARCHAR not null,
--     primary key (restaurant_no)
-- );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '분식집1', '2000', '15000' );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '분식집2', '3000', '10000' );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '일식집1', '3000', '20000' );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '일식집2', '3000', '10000' );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '중국집1', '3000', '10000' );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '중국집2', '3000', '10000' );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '한식집1', '3000', '20000' );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '한식집2', '3000', '10000' );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '양식집1', '3000', '20000' );
insert into restaurant (name, delivery_tip, min_order_price)
values ( '양식집2', '3000', '10000' );

insert into menu (name, price, restaurant_no) values ( '김밥', '2500', 1 );
insert into menu (name, price, restaurant_no) values ( '김밥', '2500', 2 );
insert into menu (name, price, restaurant_no) values ( '라면', '3000', 1 );
insert into menu (name, price, restaurant_no) values ( '라면', '3000', 2 );
insert into menu (name, price, restaurant_no) values ( '떡볶이', '4000', 1 );
insert into menu (name, price, restaurant_no) values ( '떡볶이', '4000', 2 );
insert into menu (name, price, restaurant_no) values ( '김말이', '3500', 1 );
insert into menu (name, price, restaurant_no) values ( '김말이', '3500', 2 );
insert into menu (name, price, restaurant_no) values ( '순대', '4000', 1 );
insert into menu (name, price, restaurant_no) values ( '순대', '4000', 2 );
insert into menu (name, price, restaurant_no) values ( '오징어튀김', '4000', 1 );
insert into menu (name, price, restaurant_no) values ( '오징어튀김', '4000', 2 );
insert into menu (name, price, restaurant_no) values ( '새우튀김', '4000', 1 );
insert into menu (name, price, restaurant_no) values ( '새우튀김', '4000', 2 );
insert into menu (name, price, restaurant_no) values ( '어묵튀김', '2000', 1 );
insert into menu (name, price, restaurant_no) values ( '어묵튀김', '2000', 2 );
insert into menu (name, price, restaurant_no) values ( '삼각만두', '2000', 1 );
insert into menu (name, price, restaurant_no) values ( '삼각만두', '2000', 2 );
insert into menu (name, price, restaurant_no) values ( '쿨피스', '1500', 1 );
insert into menu (name, price, restaurant_no) values ( '쿨피스', '1500', 2 );


insert into menu (name, price, restaurant_no) values ( '광어초밥', '15000', 3 );
insert into menu (name, price, restaurant_no) values ( '연어초밥', '18000', 3 );
insert into menu (name, price, restaurant_no) values ( '소바', '6000', 3 );
insert into menu (name, price, restaurant_no) values ( '우동', '6000', 3 );
insert into menu (name, price, restaurant_no) values ( '연어덮밥', '6000', 3 );
