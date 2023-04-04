create table jogo (
    id bigint auto_increment,
    nome varchar(60) not null,
    descricao text not null,
    preco decimal(10,2) not null,
    nota decimal(10,2) null,

    primary key(id)
) engine=InnoDB default charset=UTF8MB4;

create table categoria (
    id bigint auto_increment,
    nome varchar(60) not null,

    primary key(id)
) engine=InnoDB default charset=UTF8MB4;

create table plataforma (
    id bigint auto_increment,
    nome varchar(60) not null,

    primary key(id)
) engine=InnoDB default charset=UTF8MB4;


