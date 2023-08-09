create table chave_ativacao(
    id bigint not null auto_increment,
    pedido_id bigint not null,
    jogo_id bigint not null,
    chave varchar(255) not null,


    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

alter table chave_ativacao add constraint fk_chave_pedido
foreign key (pedido_id) references pedido (id);

alter table chave_ativacao add constraint fk_chave_jogo
foreign key (jogo_id) references jogo (id);
