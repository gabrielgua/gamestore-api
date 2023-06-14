create table jogo_modo (
    jogo_id bigint not null,
    modo_id bigint not null,

    primary key (jogo_id, modo_id)
) engine=InnoDB default charset=UTF8MB4;

alter table jogo_modo add constraint fk_jogo_modo_jogo
foreign key (jogo_id) references jogo (id);

alter table jogo_modo add constraint fk_jogo_modo_modo
foreign key (modo_id) references modo (id);

