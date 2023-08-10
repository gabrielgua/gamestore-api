create table compra (
    id bigint not null auto_increment,
    usuario_id bigint not null,
    jogo_id bigint not null,

    codigo_pedido varchar(255) not null,
    data_compra datetime not null,
    chave_ativacao varchar(255) not null,

    primary key (id),
    constraint fk_jogo_usuario_compra_usuario foreign key (usuario_id) references usuario (id),
    constraint fk_jogo_usuario_compra_jogo foreign key (jogo_id) references jogo (id)

) engine=InnoDB default charset=UTF8MB4;
