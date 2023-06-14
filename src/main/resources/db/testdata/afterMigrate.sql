set foreign_key_checks = 0;

delete from jogo;
delete from modo;
delete from pedido;
delete from usuario;
delete from jogo_modo;
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
alter table modo auto_increment = 1;
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
(5, 'Santa Monica Studio'),
(6, 'Capcom Co., Ltd.');

insert into jogo (id, nome, uri_nome, url_video, url_imagem, url_img_hero, data_lancamento, descricao, preco, nota, desenvolvedora_id) values
(1,
    'Dark Souls™: Remastered', 'dark-souls-remastered',
    'https://www.youtube.com/embed/nzwnFYKNNdc',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/570940/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/d645920e395fedad7bbbed0eca3fe2e0.png',
    '2018-05-23 00:00:00', 'Mas então, fez-se o fogo. Experimente novamente o jogo aclamado pela crítica e definidor de gênero que foi o início tudo. Belamente remasterizado, volte a Lordran com detalhes em alta definição a 60fps.', 129.90, 9.8, 1),
(2,
    'Dark Souls™ II: Scholar of The First Sin',
    'dark-souls-ii-scholar-of-the-first-sin',
    'https://www.youtube.com/embed/bWZ61JWLq30',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/335300/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/71dd874ff78e42aa8050469380bea669.png',
    '2015-04-01 00:00:00', 'Dark Souls™ II: Scholar of the First Sin leva a renomada obscuridade e jogabilidade viciante da franquia a um novo nível. Junte-se à jornada sombria e vivencie encontros com inimigos devastadores, perigos diabólicos e o desafio implacável.', 179.90, 7.8, 1),
(3,
    'Dark Souls™ III', 'dark-souls-iii',
    'https://www.youtube.com/embed/0RAlGv-IW4g',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/374320/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/6ed1b690afebbb531e2ddc3d7799e260.png',
    '2016-04-11 00:00:00', 'Dark Souls™ continua a ultrapassar seus próprios limites em um ambicioso novo capítulo da série que definiu um gênero e que é aclamada pela crítica. Prepare-se para abraçar a escuridão!', 259.89, 9.2, 1),
(4,
    'Counter-Strike: Global Offensive',
    'counter-strike-global-offensive',
    'https://www.youtube.com/embed/edYCtaNueQY',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/730/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/dac32acd4db4c29c230538b72f8dd87d.png',
    '2012-08-21 00:00:00', 'O Counter-Strike: Global Offensive (CS:GO) melhora a jogabilidade de ação baseada em equipes na qual foi pioneiro quando lançado há 19 anos. O CS:GO contém novos mapas, personagens e armas, além de contar com versões atualizadas de conteúdos do CS clássico (como de_dust2).', 79.90, 8.9, 3),
(5,
    'God of War',
    'god-of-war',
    'https://www.youtube.com/embed/HqQMh_tij0c',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1593500/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/33e70269806c88720e2d89ed1d3f1be3.png',
    '2022-01-14 00:00:00', 'Com a vingança contra os deuses do Olimpo em um passado distante, Kratos agora vive como um mortal no reino dos deuses e monstros nórdicos. É nesse mundo duro e implacável que ele deve lutar para sobreviver... e ensinar seu filho a fazer o mesmo.', 249.99, 9.8, 5),
(6,
    'Red Dead Redemption 2',
    'red-dead-redemption-2',
    'https://www.youtube.com/embed/SXvQ1nK4oxk',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1174180/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/964e7520947a0d3ac39504daea604d83.png',
    '2019-12-05 00:00:00', 'Red Dead Redemption 2, a épica aventura de mundo aberto da Rockstar Games aclamada pela crítica e o jogo mais bem avaliado desta geração de consoles, agora chega aprimorado para PC com conteúdos inéditos no Modo História, melhorias visuais e muito mais.', 257.90, 9.8, 2),
(7,
    'EA SPORTS™ FIFA 23',
    'ea-sports-fifa-23',
    'https://www.youtube.com/embed/o3V-GvvzjE4',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1811260/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/708a232f1a9298595d27057e2bda7fa2.png',
    '2022-09-30 00:00:00', 'EA SPORTS™ FIFA 23 traz o jogo de todo mundo aos gramados com a tecnologia HyperMotion2 proporcionando jogabilidade com ainda mais realismo, a FIFA World Cup™ masculina e feminina que chegarão ao jogo em atualizações pós-lançamento, a inclusão de clubes femininos, recursos de crossplay* e muito mais. Sinta a autenticidade inigualável com mais de 19.000 atletas, mais de 700 clubes, 100 estádios e mais de 30 ligas no FIFA 23.', 357.99, 5.9, 4),
(8,
    'Apex Legends',
    'apex-legends',
    'https://www.youtube.com/embed/innmNewjkuk',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1172470/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/e888d54c6981b4e7e92bbd874655a3bf.jpg',
    '2019-02-04 00:00:00', 'Apex Legends é o premiado jogo de tiro em primeira pessoa de heróis e heroínas da Respawn Entertainment. Domine um elenco cada vez mais amplo de personagens lendários com habilidades poderosas, experimente partidas estratégicas e a jogabilidade inovadora na próxima evolução do jogo de tiro de heroísmo.', 0.0, 8.8, 4),
(9,
    'Sekiro™: Shadows Die Twice',
    'sekiro-shadows-die-twice',
    'https://www.youtube.com/embed/4OgoTZXPACo',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/814380/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/8b2ced7428b64f653036b4a67d32302b.jpg',
    '2019-03-21 00:00:00', 'Trilhe seu próprio caminho de vingança nesta premiada aventura da FromSoftware, os criadores de Bloodborne e da franquia Dark Souls. Obtenha vingança. Retome sua honra. Mate astuciosamente.', 199.90, 9.1, 1),
(10,
    'Resident Evil 4 (2023)',
    'resident-evil-4-2023',
    'https://www.youtube.com/embed/j5Xv2lM9wes',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/2050650/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/5ccb9d0e9173e00230298318ed6ae164.png',
    '2023-03-23 00:00:00', 'Sobrevivência é apenas o começo. Seis anos se passaram desde o desastre biológico em Raccoon City. Leon S. Kennedy, um dos sobreviventes, segue o rastro da raptada filha do presidente até uma vila europeia isolada, onde há algo terrivelmente errado com os habitantes.', 249.00, 9.6, 2);

insert into requisito (id, jogo_id, tipo, observacoes, sistema, memoria, processador, placa_de_video, armazenamento) values
(1, 1, 'MINIMOS', 'Low Settings, 60 FPS @ 1080p', 'Windows 7 64-bit', '6 GB de RAM', 'Intel Core i5-2300 2.8 GHz / AMD FX-6300, 3.5 GHz', 'GeForce GTX 460, 1 GB / Radeon HD 6870, 1 GB', '8 GB de espaço disponível'),
(2, 1, 'RECOMENDADOS', 'Ultra Settings, 60 FPS @ 1080p', 'Windows 10 64-bit', '8 GB de RAM', 'Intel Core i5-4570 3.2 GHz / AMD FX-8350 4.2 GHz', 'GeForce GTX 660, 2 GB / Radeon HD 7870, 2 GB', '8 GB de espaço disponível'),

(4, 2, 'MINIMOS', 'Low Settings, 60 FPS @ 1080p', 'Windows 7 64-bit', '4 GB de RAM', 'AMD® A8 3870 3,6 Ghz or Intel® Core ™ i3 2100 3.1Ghz', 'NVIDIA® GeForce GTX 465 / ATI Radeon TM HD 6870', '23 GB de espaço disponível'),
(5, 2, 'RECOMENDADOS', 'Ultra Settings, 60 FPS @ 1080p', 'Windows 10 64-bit', '8 GB de RAM', 'AMD® FX 8150 3.6 GHz or Intel® Core™ i7 2600 3.4 GHz', 'NVIDIA® GeForce® GTX 750, ATI Radeon™ HD 7850', '23 GB de espaço disponível'),

(7, 3, 'MINIMOS', '60 FPS @ 720p, Low', 'Windows 7 64-bit', '4 GB de RAM', 'Intel Core i3-2100 / AMD® FX-6300', 'NVIDIA® GeForce GTX 750 Ti / ATI Radeon HD 7950', '25 GB de espaço disponível'),
(8, 3, 'RECOMENDADOS', '60 FPS @ 1080p, Ultra', 'Windows 10 64-bit', '8 GB de RAM', 'Intel Core i7-3770 / AMD® FX-8350', 'NVIDIA® GeForce GTX 970 / ATI Radeon R9 series', '25 GB de espaço disponível'),

(10, 4, 'MINIMOS', null, 'Windows® 7/Vista/XP', '2 GB de RAM', 'Intel® Core™ 2 Duo E6600 ou AMD Phenom™ X3 8750', 'A placa de vídeo precisa ter 256 MB ou mais de memória e ser compatível com DirectX 9 e Pixel Shader 3.0', '15 GB de espaço disponível');

insert into categoria (id, nome) values
(1, 'FPS'), (2, 'RPG'), (3, 'Soulslike'), (4, 'Simulador'), (5, 'Ação'), (6, 'Aventura'), (7, 'Tiro'), (8, 'Esportes'), (9, 'Metroidvania'), (10, 'Difícil');

insert into plataforma (id, nome) values
(1, 'PC'), (2, 'PS4'), (3, 'PS5'), (4, 'Xbox ONE'), (5, 'Xbox Series X/S'), (6, 'Nintendo Switch');

insert into modo (id, nome) values
(1, 'Um jogador'), (2, 'Multijogador'), (3, 'PvP online'), (4, 'PvE online'), (5, 'Cooperativo on-line');

insert into forma_pagamento (id, nome) values
(1, 'Pix'), (2, 'Boleto'), (3, 'Cartão de crédito'), (4, 'Cartão de débito'), (5, 'Cartão presente');

insert into jogo_modo (jogo_id, modo_id) values
(1, 1), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 3), (3, 4), (3, 5),
(4, 2), (4, 3),
(5, 1),
(6, 1), (6, 2),
(7, 1), (7, 2), (7, 3), (7, 4), (7, 5),
(8, 2), (8, 3),
(9, 1),
(10, 1);

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
(9, 1), (9, 2), (9, 3), (9, 4), (9, 5),
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


