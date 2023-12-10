create table book
(
    id          bigint auto_increment
        primary key,
    author      varchar(64)   not null,
    description varchar(2048) null,
    image       text          null,
    inventory   int           not null,
    isbn        varchar(32)   not null,
    name        varchar(128)  not null,
    price       double        not null,
    sales       int           not null,
    is_delete   bit           not null,
    constraint UK_ehpdfjpu1jm3hijhj4mm0hx9h
        unique (isbn)
);

create table tag
(
    id      bigint auto_increment
        primary key,
    content varchar(255) not null,
    constraint UK_lkyiy3gbkiiffxufqh5ud57w3
        unique (content)
);

create table book_tag
(
    book_id bigint not null,
    tag_id  bigint not null,
    constraint FKdrc33u5ufw8rdvajeveowgx7g
        foreign key (book_id) references book (id),
    constraint FKrxw4xl05l6ns1763bq284e7m2
        foreign key (tag_id) references tag (id)
);

create table user
(
    id       bigint auto_increment
        primary key,
    avatar   varchar(10240) null,
    email    varchar(128)   null,
    name     varchar(128)   null,
    notes    varchar(10240) null,
    role     smallint       not null,
    status   bit            not null,
    username varchar(128)   not null,
    constraint UK_sb8bbouer5wak8vyiiy4pf2bx
        unique (username)
);

create table cart_item
(
    id      bigint auto_increment
        primary key,
    amount  int    not null,
    price   double not null,
    book_id bigint not null,
    user_id bigint null,
    constraint FKis5hg85qbs5d91etr4mvd4tx6
        foreign key (book_id) references book (id),
    constraint FKjnaj4sjyqjkr4ivemf9gb25w
        foreign key (user_id) references user (id)
);

create table orders
(
    id            bigint auto_increment
        primary key,
    purchase_time datetime(6) not null,
    total_price   double      not null,
    user_id       bigint      not null,
    is_delete     bit         not null,
    constraint FKel9kyl84ego2otj2accfd8mr7
        foreign key (user_id) references user (id)
);

create table order_item
(
    id        bigint auto_increment
        primary key,
    amount    int    not null,
    price     double not null,
    book_id   bigint not null,
    order_id  bigint not null,
    is_delete bit    not null,
    constraint FKb033an1f8qmpbnfl0a6jb5njs
        foreign key (book_id) references book (id),
    constraint FKt4dc2r9nbvbujrljv3e23iibt
        foreign key (order_id) references orders (id)
);

create table user_auth
(
    id       bigint auto_increment
        primary key,
    password varchar(128) not null,
    user_id  bigint       null,
    constraint FK8pefkt453l7vq56pt2qoo5h4p
        foreign key (user_id) references user (id)
);


