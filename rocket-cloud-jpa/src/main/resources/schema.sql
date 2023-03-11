create table if not exists Rocket (
  id identity,
  name varchar(50) not null,
  sort varchar(50) not null,
  speed_max double,
  angle_min double,
  angle_max double,
  height_max double,
  distance_max integer,
  created_at timestamp
);

create table if not exists Wind_Direction (
  id identity,
  wind_direction_name varchar(50) not null
);

create table if not exists Flight (
  id identity,
  speed double,
  distance integer,
  height integer,
  angle double,
  wind_speed double,
  created_at timestamp,
  fk_rocket_id bigint not null,
  fk_wind_direction_id bigint not null
);

alter table Flight
    add foreign key (fk_rocket_id) references Rocket(id);

alter table Flight
    add foreign key (fk_wind_direction_id) references Wind_Direction(id);
