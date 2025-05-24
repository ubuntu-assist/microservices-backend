create extension if not exists "uuid-ossp";

create table if not exists category
(
    id uuid default uuid_generate_v4() not null primary key,
    description varchar(255),
    name varchar(255)
);

create table if not exists product
(
    id uuid default uuid_generate_v4() not null primary key,
    available_quantity double precision not null,
    description varchar(255),
    name varchar(255),
    price numeric(38, 2),
    category_id uuid
        constraint fk1mtsbur82frn64de7balymq9s references category(id)
);