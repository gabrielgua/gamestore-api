create table usuario_jogo (
    usuario_id bigint not null,
    jogo_id bigint not null,

    primary key (usuario_id, jogo_id)
) engine=InnoDB default charset=UTF8MB4;

alter table usuario_jogo add constraint fk_usuario_jogo_usuario
foreign key (usuario_id) references usuario (id);

alter table usuario_jogo add constraint fk_usuario_jogo_jogo
foreign key (jogo_id) references jogo (id);