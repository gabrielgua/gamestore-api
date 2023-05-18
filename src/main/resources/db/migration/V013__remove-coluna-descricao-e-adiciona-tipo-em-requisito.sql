alter table requisito drop column descricao;
alter table requisito add column tipo varchar(50) not null after jogo_id;