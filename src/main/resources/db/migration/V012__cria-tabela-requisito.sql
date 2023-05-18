create table requisito (
    id bigint not null auto_increment,
    jogo_id bigint not null,
    descricao varchar(250) not null,
    sistema varchar(150) null,
    memoria varchar(10) null,
    processador varchar(250) null,
    placa_de_video varchar(250) null,
    armazenamento varchar(150) null,

    primary key (id),
    constraint fk_requisitos_jogo foreign key (jogo_id) references jogo (id)
) engine=InnoDB default charset=utf8;