
create table ADDRESS
(
    ID                  integer unique      not null,
    STREET              varchar(52)         not null,
    HOUSE_NUMBER        varchar(52)         not null,
    POST_CODE           varchar(52)         not null,
    CITY                varchar(52)         not null,
    CONSTRAINT pk_address_id PRIMARY KEY (ID)
);

CREATE SEQUENCE address_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 10000
    cycle;



create table CUSTOMER
(
    ID                  integer unique      not null,
    FIRST_NAME          varchar(52)         not null,
    LAST_NAME           varchar(52)         not null,
    EMAIL               varchar(52) unique  not null,
    ADDRESS_ID          integer             not null,
    PHONE_NUMBER        varchar(52)         not null,
    PASSWORD            varchar(52)         not null,

    CONSTRAINT pk_customer_id PRIMARY KEY (ID),
    CONSTRAINT fk_address_id FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS (ID)
);

CREATE SEQUENCE customer_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 10000
    cycle;

create table CUSTOMER_PRIVILEGE
(
    CUSTOMER_ID         integer             not null,
    PRIVILEGE           varchar(52)         not null,

    PRIMARY KEY (CUSTOMER_ID, PRIVILEGE),
    CONSTRAINT fk_customer_id FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (ID)
);


create table PRODUCT
(
    ID                  integer unique      not null,
    NAME                varchar(52)         not null,
    DESCRIPTION         varchar(512)        not null,
    PRICE_IN_EURO       decimal             not null,

    CONSTRAINT pk_product_id PRIMARY KEY (ID)
);

CREATE SEQUENCE product_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 10000
    cycle;

create table ITEM
(
    ID                  integer unique      not null,
    PRODUCT_ID          integer             not null,
    STATUS              varchar(52)         not null,

    CONSTRAINT pk_item_id PRIMARY KEY (ID),
    CONSTRAINT fk_product_id FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (ID)
);

CREATE SEQUENCE item_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 10000
    cycle;

create table OORDER
(
    ID                  integer unique      not null,
    CUSTOMER_ID         integer             not null,

    CONSTRAINT pk_oorder_id PRIMARY KEY (ID),
    CONSTRAINT fk_customer_o_id FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (ID)
);

CREATE SEQUENCE oorder_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 10000
    cycle;

create table ORDER_LINE
(
    ID                  integer unique      not null,
    OORDER_ID           integer             not null,
    DELIVERY_DATE       date                not null,

    CONSTRAINT pk_line_id PRIMARY KEY (ID),
    CONSTRAINT fk_oorder_id FOREIGN KEY (OORDER_ID) REFERENCES OORDER (ID)
);

CREATE SEQUENCE line_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 10000
    cycle;

create table ORDER_ITEM
(
    ID                  integer unique      not null,
    ITEM_ID                integer unique      not null,
    PRICE               decimal             not null,
    LINE_ID                integer             not null,


    CONSTRAINT pk_order_item_id PRIMARY KEY (ID),
    CONSTRAINT fk_item_id FOREIGN KEY (ITEM_ID) REFERENCES ITEM (ID),
    CONSTRAINT fk_line_id FOREIGN KEY (LINE_ID) REFERENCES ORDER_LINE (ID)
);

CREATE SEQUENCE order_item_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 10000
    cycle;

