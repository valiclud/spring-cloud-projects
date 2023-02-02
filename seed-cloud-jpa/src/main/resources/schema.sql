create table if not exists Seed_Starter (
id identity,
date_planted timestamp not null,
covered boolean not null,
packing_type varchar(10) not null
);

create table if not exists Feature (
id identity,
name varchar(50) not null
);

create table if not exists Feature_Ref (
feature_id identity,
seed_starter_id bigint not null
);

create table if not exists Seed_Row (
id identity,
seeds_per_cell bigint not null,
fk_seed_starter bigint not null,
fk_variety bigint not null
);

create table if not exists Variety (
variety_id identity,
name varchar(50) not null
);

alter table Seed_Row
    add foreign key (fk_variety) references Variety(variety_id);
alter table Seed_Row
    add foreign key (fk_seed_starter) references Seed_Starter(id);
alter table Feature_Ref
    add foreign key (feature_id) references Feature(id);
alter table Feature_Ref
    add foreign key (seed_starter_id) references Seed_Starter(id);


