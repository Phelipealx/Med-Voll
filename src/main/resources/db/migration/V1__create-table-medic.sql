create table medic(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    specialty varchar(100) not null,
    streetName varchar(100) not null,
    neighbourhood varchar(100) not null,
    postalCode varchar(10) not null,
    complement varchar(100),
    number varchar(20),
    state char(2) not null,
    city varchar(100) not null,
    country varchar(100) not null,

    primary key(id)

);