create table pedido_jogo (
    pedido_id bigint not null,
    jogo_id bigint not null,

    primary key (pedido_id, jogo_id)

) engine=InnoDB default charset=UTF8MB4;

alter table pedido_jogo add constraint fk_pedido_jogo_pedido
foreign key (pedido_id) references pedido (id);

alter table pedido_jogo add constraint fk_pedido_jogo_jogo
foreign key (jogo_id) references jogo (id);