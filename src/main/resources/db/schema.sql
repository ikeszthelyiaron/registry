create database registry_db collate SQL_Latin1_General_CP1_CI_AS
    go

grant connect on database :: registry_db to dbo
go

grant view any column encryption key definition, view any column master key definition on database :: registry_db to [public]
go

create table dbo.address
(
    id           bigint identity
        primary key,
    city         varchar(255),
    house_number varchar(255),
    address_type varchar(255)
        check ([address_type] = 'permanent' OR [address_type] = 'temporary'),
    street       varchar(255),
    zip_code     int,
    person_id    bigint
)
    go

create table dbo.person
(
    id                bigint identity
        primary key,
    name              varchar(255),
    permanent_address bigint
        constraint FK7tuppr9cqib1yyo3gyafq6uuh
            references dbo.address,
    temporary_address bigint
        constraint FK44ur5j4s3sc5l06ai376n5dnu
            references dbo.address
)
    go

alter table dbo.address
    add constraint FK81ihijcn1kdfwffke0c0sjqeb
        foreign key (person_id) references dbo.person
    go

create table dbo.contact_detail
(
    id         bigint identity
        primary key,
    identifier varchar(255),
    platform   varchar(255)
        check ([platform] = 'SKYPE' OR [platform] = 'TWITTER' OR [platform] = 'PHONE' OR [platform] = 'EMAIL'),
    person_id  bigint
        constraint FKcausnvwrke9q3ie17c6scerrb
            references dbo.person
)
    go

create unique index UKi4dgqrxojj88cf6txslb3dexi
    on dbo.person (permanent_address)
    where [permanent_address] IS NOT NULL
go

create unique index UKp0ukh00i9e1r26swvqbldh6nv
    on dbo.person (temporary_address)
    where [temporary_address] IS NOT NULL
go

