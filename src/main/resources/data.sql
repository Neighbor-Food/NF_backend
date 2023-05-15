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
values ( 'eglechwang@kumoh.ac.kr', '1234', '나이름', '', '국민', '000-0000-000', true );

insert into restaurant (name, category, delivery_tip, min_order_price)
values ('분식집1', '분식', '2000', '15000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('분식집2', '분식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('일식집1', '일식', '3000', '20000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('일식집2', '일식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('중국집1', '중식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('중국집2', '중식', '3000', '10000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('한식집1', '한식', '3000', '20000');
insert into restaurant (name, category, delivery_tip, min_order_price)
values ('한식집2', '한식', '3000', '10000');
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
