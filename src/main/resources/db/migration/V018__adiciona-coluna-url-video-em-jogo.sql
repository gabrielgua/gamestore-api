alter table jogo add column url_video varchar(250) not null after uri_nome;
alter table jogo add column url_imagem varchar(250) not null after url_video;