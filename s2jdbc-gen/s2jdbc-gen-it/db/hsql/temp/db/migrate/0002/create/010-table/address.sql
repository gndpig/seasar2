create table ADDRESS (
    ID integer generated by default as identity(start with 1),
    CITY varchar(255),
    constraint ADDRESS_PK primary key(ID)
);
