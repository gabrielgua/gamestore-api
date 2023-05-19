alter table jogo add column desenvolvedora_id bigint not null after id;

set foreign_key_checks = 0;

alter table jogo add constraint fk_jogo_desenvolvedora
foreign key (desenvolvedora_id) references desenvolvedora (id);

set foreign_key_checks = 1;
