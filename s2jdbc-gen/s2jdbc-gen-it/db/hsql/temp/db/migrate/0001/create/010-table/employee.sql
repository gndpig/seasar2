create table EMPLOYEE (
    ID integer generated by default as identity(start with 1),
    FIRST_NAME varchar(255) not null,
    LAST_NAME varchar(255) not null,
    DEPARTMENT_ID integer,
    ADDRESS_ID integer,
    constraint EMPLOYEE_PK primary key(ID)
);
