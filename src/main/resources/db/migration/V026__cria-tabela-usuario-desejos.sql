create table usuario_desejos(
    usuario_id bigint not null,
    jogo_id bigint not null,

    primary key (usuario_id, jogo_id),
    constraint fk_usuario_desejos_usuario foreign key (usuario_id) references usuario (id),
    constraint fk_usuario_desejos_jogo foreign key (jogo_id) references jogo (id)
) engine=InnoDB default charset=UTF8MB4;