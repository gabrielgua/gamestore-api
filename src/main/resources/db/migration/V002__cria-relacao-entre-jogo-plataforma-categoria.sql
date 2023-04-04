create table jogo_categoria (
    jogo_id bigint not null,
    categoria_id bigint not null,

    primary key(jogo_id, categoria_id)

) engine=InnoDB default charset=UTF8MB4;

alter table jogo_categoria add constraint fk_jogo_categoria_jogo
foreign key (jogo_id) references jogo (id);

alter table jogo_categoria add constraint fk_jogo_categoria_categoria
foreign key (categoria_id) references categoria (id);

create table jogo_plataforma (
    jogo_id bigint not null,
    plataforma_id bigint not null,

    primary key(jogo_id, plataforma_id)
) engine=InnoDB default charset=UTF8MB4;

alter table jogo_plataforma add constraint fk_jogo_plataforma_jogo
foreign key (jogo_id) references jogo (id);

alter table jogo_plataforma add constraint fk_jogo_plataforma_plataforma
foreign key (plataforma_id) references plataforma (id);

