create table pedido (
    id bigint not null auto_increment,
    codigo varchar(36) not null,
    valor_total decimal(10,2) not null,
    data_criacao datetime not null,
    data_confirmacao datetime null,
    data_cancelamento datetime null,
    data_reembolso datetime null,
    status varchar(15) not null,

    usuario_id bigint not null,

    primary key (id),
    constraint fk_pedido_usuario foreign key (usuario_id) references usuario (id)
) engine=InnoDB default charset=UTF8MB4;

update pedido set codigo = uuid();
alter table pedido add constraint uk_pedido_codigo unique (codigo);


