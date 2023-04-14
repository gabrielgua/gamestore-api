create table usuario (
    id bigint not null auto_increment,
    nome varchar(255) null,
    username varchar(20) not null,
    email varchar(255) not null,
    senha varchar(255) not null,
    data_cadastro datetime not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

