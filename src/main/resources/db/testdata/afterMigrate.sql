set foreign_key_checks = 0;

delete from jogo;
delete from pedido;
delete from usuario;
delete from requisito;
delete from categoria;
delete from plataforma;
delete from pedido_jogo;
delete from usuario_jogo;
delete from jogo_categoria;
delete from desenvolvedora;
delete from jogo_plataforma;
delete from forma_pagamento;

set foreign_key_checks = 1;

alter table jogo auto_increment = 1;
alter table pedido auto_increment = 1;
alter table usuario auto_increment = 1;
alter table requisito auto_increment = 1;
alter table categoria auto_increment = 1;
alter table plataforma auto_increment = 1;
alter table desenvolvedora auto_increment = 1;
alter table forma_pagamento auto_increment = 1;

insert into desenvolvedora(id, nome) values
(1, 'FromSoftware, Inc'),
(2, 'Rockstar Games'),
(3, 'Valve, Hidden Path Entertainment'),
(4, 'Electronic Arts'),
(5, 'Santa Monica Studio');

insert into jogo (id, nome, uri_nome, data_lancamento, descricao, preco, nota, desenvolvedora_id) values
(1, 'Dark Souls™: Remastered', 'dark-souls-remastered', '2018-05-23 00:00:00', 'Mas então, fez-se o fogo. Experimente novamente o jogo aclamado pela crítica e definidor de gênero que foi o início tudo. Belamente remasterizado, volte a Lordran com detalhes em alta definição a 60fps.', 129.90, 9.8, 1),
(2, 'Dark Souls™ II: Scholar of The First Sin', 'dark-souls-ii-scholar-of-the-first-sin', '2015-04-01 00:00:00', 'DARK SOULS™ II: Scholar of the First Sin leva a renomada obscuridade e jogabilidade viciante da franquia a um novo nível. Junte-se à jornada sombria e vivencie encontros com inimigos devastadores, perigos diabólicos e o desafio implacável.', 179.90, 7.8, 1),
(3, 'Dark Souls™ III', 'dark-souls-iii', '2016-04-11 00:00:00', 'DARK SOULS™ continua a ultrapassar seus próprios limites em um ambicioso novo capítulo da série que definiu um gênero e que é aclamada pela crítica. Prepare-se para abraçar a escuridão!', 259.89, 9.2, 1),
(4, 'Counter-Strike: Global Offensive', 'counter-strike-global-offensive', '2012-08-21 00:00:00', 'O Counter-Strike: Global Offensive (CS:GO) melhora a jogabilidade de ação baseada em equipes na qual foi pioneiro quando lançado há 19 anos. O CS:GO contém novos mapas, personagens e armas, além de contar com versões atualizadas de conteúdos do CS clássico (como de_dust2).', 79.90, 8.9, 3),
(5, 'God of War', 'god-of-war', '2022-01-14 00:00:00', 'Com a vingança contra os deuses do Olimpo em um passado distante, Kratos agora vive como um mortal no reino dos deuses e monstros nórdicos. É nesse mundo duro e implacável que ele deve lutar para sobreviver... e ensinar seu filho a fazer o mesmo.', 249.99, 9.8, 5),
(6, 'Red Dead Redemption 2', 'red-dead-redemption-2', '2019-12-05 00:00:00', 'Red Dead Redemption 2, a épica aventura de mundo aberto da Rockstar Games aclamada pela crítica e o jogo mais bem avaliado desta geração de consoles, agora chega aprimorado para PC com conteúdos inéditos no Modo História, melhorias visuais e muito mais.', 257.90, 9.8, 2),
(7, 'EA SPORTS™ FIFA 23', 'ea-sports-fifa-23', '2022-09-30 00:00:00', 'FIFA 23 traz o Jogo de Todo Mundo aos gramados com a tecnologia HyperMotion2, a FIFA World Cup™ masculina e feminina (lançamento durante a temporada), times femininos, recursos de crossplay* e muito mais.', 357.99, 5.9, 4),
(8, 'Apex Legends', 'apex-legends', '2019-02-04 00:00:00', 'Apex Legends é o premiado jogo de tiro em primeira pessoa de heróis e heroínas da Respawn Entertainment. Domine um elenco cada vez mais amplo de personagens lendários com habilidades poderosas, experimente partidas estratégicas e a jogabilidade inovadora na próxima evolução do jogo de tiro de heroísmo.', 0.0, 8.8, 4),
(9, 'Bloodborne', 'bloodborne', '2015-03-25 00:00:00', 'Enfrente seus pesadelos enquanto busca respostas na antiga cidade de Yharnam, agora amaldiçoada com uma estranha doença endêmica que se espalha pelas ruas como fogo. Perigo, morte e loucura estão em cada canto deste sombrio e horroroso mundo, e você deve descobrir seus segredos mais sombrios para sobreviver.', 199.99, 9.1, 1),
(10, 'Grand Theft Auto V', 'grand-theft-auto-v', '2013-09-17 00:00:00', 'Grand Theft Auto V para PC oferece aos jogadores a opção de explorar o gigantesco e premiado mundo de Los Santos e Blaine County em resoluções de até 4K e além, assim como a chance de experimentar o jogo rodando a 60 FPS (quadros por segundo).', 89.9, 9.6, 2);

insert into requisito (id, jogo_id, tipo, observacoes, sistema, memoria, processador, placa_de_video, armazenamento) values
(1, 1, 'MINIMOS', 'Low Settings, 60 FPS @ 1080p', 'Windows 7 64-bit', '6 GB de RAM', 'Intel Core i5-2300 2.8 GHz / AMD FX-6300, 3.5 GHz', 'GeForce GTX 460, 1 GB / Radeon HD 6870, 1 GB', '8 GB de espaço disponível'),
(2, 1, 'RECOMENDADOS', 'Ultra Settings, 60 FPS @ 1080p', 'Windows 10 64-bit', '8 GB de RAM', 'Intel Core i5-4570 3.2 GHz / AMD FX-8350 4.2 GHz', 'GeForce GTX 660, 2 GB / Radeon HD 7870, 2 GB', '8 GB de espaço disponível'),
(3, 1, 'CONSOLES', null, null, null, null, null, '10 GB de espaço disponíveis.'),

(4, 2, 'MINIMOS', 'Low Settings, 60 FPS @ 1080p', 'Windows 7 64-bit', '4 GB de RAM', 'AMD® A8 3870 3,6 Ghz or Intel® Core ™ i3 2100 3.1Ghz', 'NVIDIA® GeForce GTX 465 / ATI Radeon TM HD 6870', '23 GB de espaço disponível'),
(5, 2, 'RECOMENDADOS', 'Ultra Settings, 60 FPS @ 1080p', 'Windows 10 64-bit', '8 GB de RAM', 'AMD® FX 8150 3.6 GHz or Intel® Core™ i7 2600 3.4 GHz', 'NVIDIA® GeForce® GTX 750, ATI Radeon™ HD 7850', '23 GB de espaço disponível'),
(6, 2, 'CONSOLES', null, null, null, null, null, '25 GB de espaço disponíveis.'),

(7, 3, 'MINIMOS', '60 FPS @ 720p, Low', 'Windows 7 64-bit', '4 GB de RAM', 'Intel Core i3-2100 / AMD® FX-6300', 'NVIDIA® GeForce GTX 750 Ti / ATI Radeon HD 7950', '25 GB de espaço disponível'),
(8, 3, 'RECOMENDADOS', '60 FPS @ 1080p, Ultra', 'Windows 10 64-bit', '8 GB de RAM', 'Intel Core i7-3770 / AMD® FX-8350', 'NVIDIA® GeForce GTX 970 / ATI Radeon R9 series', '25 GB de espaço disponível'),
(9, 3, 'CONSOLES', null, null, null, null, null, '28 GB de espaço disponíveis.'),

(10, 4, 'MINIMOS', null, 'Windows® 7/Vista/XP', '2 GB de RAM', 'Intel® Core™ 2 Duo E6600 ou AMD Phenom™ X3 8750', 'A placa de vídeo precisa ter 256 MB ou mais de memória e ser compatível com DirectX 9 e Pixel Shader 3.0', '15 GB de espaço disponível');

insert into categoria (id, nome) values
(1, 'FPS'), (2, 'RPG'), (3, 'Souls-Like'), (4, 'Simulador'), (5, 'Ação'), (6, 'Aventura'), (7, 'Tiro'), (8, 'Esportes');

insert into plataforma (id, nome) values
(1, 'PC'), (2, 'PS4'), (3, 'PS5'), (4, 'Xbox ONE'), (5, 'Xbox Series X/S'), (6, 'Nintendo Switch');

insert into forma_pagamento (id, nome) values
(1, 'Pix'), (2, 'Boleto'), (3, 'Cartão de crédito'), (4, 'Cartão de débito'), (5, 'Cartão presente');

insert into jogo_categoria (jogo_id, categoria_id) values
(1, 2), (1, 3), (1, 6),
(2, 2), (2, 3), (2, 6),
(3, 2), (3, 3), (3, 6),
(4, 1), (4, 5), (4, 6), (4, 7),
(5, 5), (5, 6),
(6, 2), (6, 5),
(7, 4), (7, 8),
(8, 1), (8, 5), (8, 6), (8, 7),
(9, 2), (9, 3), (9, 5), (9, 6),
(10, 4), (10, 5), (10, 7);

insert into jogo_plataforma (jogo_id, plataforma_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5),
(4, 1),
(5, 1), (5, 2), (5, 3), (5, 4), (5, 5),
(6, 1), (6, 2), (6, 3), (6, 4), (6, 5),
(7, 1), (7, 2), (7, 3), (7, 4), (7, 5),
(8, 1), (8, 2), (8, 3), (8, 4), (8, 5),
(9, 2), (9, 3),
(10, 1), (10, 2), (10, 3), (10, 4), (10, 5);

insert into usuario (id, nome, username, tipo, email, senha, data_cadastro) values
(1, "Gabriel", "opaco", "ADMIN", "gabriel.opaco@email.com", "$2a$12$Z7eR/rUV9CusNU3IEDtyhOhmos/sAQOv5W7MuEd/tU9lejmCdmzZ2", utc_timestamp),
(2, null, "usuario", "USER", "usuario.gamer@email.com", "$2a$12$Z7eR/rUV9CusNU3IEDtyhOhmos/sAQOv5W7MuEd/tU9lejmCdmzZ2", utc_timestamp);

insert into usuario_jogo (usuario_id, jogo_id) values
(1, 1), (1, 3), (1, 5),
(2, 2);

insert into pedido (id, codigo, valor_total, data_criacao, data_confirmacao, data_cancelamento, data_reembolso, status, usuario_id, forma_pagamento_id) values
(1, "7221549c-db06-11ed-afa1-0242ac120002", 309.80, utc_timestamp, null, null, null, "CONFIRMADO", 1, 3),
(2, "b751b070-db06-11ed-afa1-0242ac120002", 79.90, utc_timestamp, null, utc_timestamp, null, "CANCELADO", 1, 2),
(3, "d5e719f8-db06-11ed-afa1-0242ac120002", 259.98, utc_timestamp, null,null, null, "CRIADO", 2, 1);

insert into pedido_jogo (pedido_id, jogo_id) values
(1, 1), (1, 2),
(2, 4),
(3, 3);


