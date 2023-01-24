create table if not exists Taco_Order (
  id identity,
  delivery_Name varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City varchar(50) not null,
  delivery_State varchar(2) not null,
  delivery_Zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  taco_order_id bigint not null,
  taco_order_key bigint not null,
  created_at timestamp not null
);
 
create table if not exists Ingredient_Ref (
  id bigint not null,
  taco bigint not null,
  taco_key bigint not null
);
 
create table if not exists Ingredient (
  id identity,
  code varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists Applicationuser (
  id identity,
  username varchar(25) not null,
  password varchar(255) not null,
  fullname varchar(25) not null,
  street varchar(25) not null,
  city varchar(25) not null,
  state varchar(25) not null,
  zip varchar(25) not null,
  phonenumber varchar(25) not null
);

alter table Taco
    add foreign key (taco_order_id) references Taco_Order(id);

alter table Ingredient_Ref
    add foreign key (id) references Ingredient(id);
    
alter table Ingredient_Ref
    add foreign key (id) references Taco(id);