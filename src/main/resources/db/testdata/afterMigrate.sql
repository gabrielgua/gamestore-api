set foreign_key_checks = 0;

delete from jogo;
delete from modo;
delete from pedido;
delete from compra;
delete from usuario;
delete from jogo_modo;
delete from requisito;
delete from categoria;
delete from plataforma;
delete from pedido_jogo;
delete from jogo_categoria;
delete from desenvolvedora;
delete from jogo_plataforma;
delete from forma_pagamento;
delete from usuario_desejos;

set foreign_key_checks = 1;

alter table jogo auto_increment = 1;
alter table modo auto_increment = 1;
alter table pedido auto_increment = 1;
alter table compra auto_increment = 1;
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
(6, 'Capcom Co., Ltd.'),
(7, 'The Game Kitchen'),
(8, 'CD PROJEKT RED'),
(9, 'Playground Games'),
(10, 'NetherRealm Studios');

insert into jogo (id, nome, uri_nome, url_video, url_imagem, url_img_hero, capsule_img, data_lancamento, descricao, preco, nota, desenvolvedora_id) values
(1,
    'Dark Souls™: Remastered', 'dark-souls-remastered',
    'https://www.youtube.com/embed/nzwnFYKNNdc',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/570940/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/570940/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/570940/library_600x900_2x.jpg',
    '2018-05-23 00:00:00', 'Mas então, fez-se o fogo. Experimente novamente o jogo aclamado pela crítica e definidor de gênero que foi o início tudo. Belamente remasterizado, volte a Lordran com detalhes em alta definição a 60fps.', 129.90, 9.8, 1),
(2,
    'Dark Souls™ II: Scholar of The First Sin',
    'dark-souls-ii-scholar-of-the-first-sin',
    'https://www.youtube.com/embed/bWZ61JWLq30',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/335300/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/335300/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/335300/library_600x900_2x.jpg',
    '2015-04-01 00:00:00', 'Dark Souls™ II: Scholar of the First Sin leva a renomada obscuridade e jogabilidade viciante da franquia a um novo nível. Junte-se à jornada sombria e vivencie encontros com inimigos devastadores, perigos diabólicos e o desafio implacável.', 179.90, 7.8, 1),
(3,
    'Dark Souls™ III', 'dark-souls-iii',
    'https://www.youtube.com/embed/0RAlGv-IW4g',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/374320/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/374320/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/374320/library_600x900_2x.jpg',
    '2016-04-11 00:00:00', 'Dark Souls™ continua a ultrapassar seus próprios limites em um ambicioso novo capítulo da série que definiu um gênero e que é aclamada pela crítica. Prepare-se para abraçar a escuridão!', 259.89, 9.2, 1),
(4,
    'Counter-Strike: Global Offensive',
    'counter-strike-global-offensive',
    'https://www.youtube.com/embed/edYCtaNueQY',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/730/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/730/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/730/library_600x900_2x.jpg',
    '2012-08-21 00:00:00', 'O Counter-Strike: Global Offensive (CS:GO) melhora a jogabilidade de ação baseada em equipes na qual foi pioneiro quando lançado há 19 anos. O CS:GO contém novos mapas, personagens e armas, além de contar com versões atualizadas de conteúdos do CS clássico (como de_dust2).', 79.90, 8.9, 3),
(5,
    'God of War',
    'god-of-war',
    'https://www.youtube.com/embed/HqQMh_tij0c',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1593500/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1593500/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1593500/library_600x900_2x.jpg',
    '2022-01-14 00:00:00', 'Com a vingança contra os deuses do Olimpo em um passado distante, Kratos agora vive como um mortal no reino dos deuses e monstros nórdicos. É nesse mundo duro e implacável que ele deve lutar para sobreviver... e ensinar seu filho a fazer o mesmo.', 249.99, 9.8, 5),
(6,
    'Red Dead Redemption 2',
    'red-dead-redemption-2',
    'https://www.youtube.com/embed/SXvQ1nK4oxk',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1174180/capsule_616x353.jpg',
    'https://cdn2.steamgriddb.com/file/sgdb-cdn/hero/964e7520947a0d3ac39504daea604d83.png',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1174180/library_600x900_2x.jpg',
    '2019-12-05 00:00:00', 'Red Dead Redemption 2, a épica aventura de mundo aberto da Rockstar Games aclamada pela crítica e o jogo mais bem avaliado desta geração de consoles, agora chega aprimorado para PC com conteúdos inéditos no Modo História, melhorias visuais e muito mais.', 257.90, 9.8, 2),
(7,
    'EA SPORTS™ FIFA 23',
    'ea-sports-fifa-23',
    'https://www.youtube.com/embed/o3V-GvvzjE4',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1811260/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1811260/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1811260/library_600x900_2x.jpg',
    '2022-09-30 00:00:00', 'EA SPORTS™ FIFA 23 traz o jogo de todo mundo aos gramados com a tecnologia HyperMotion2 proporcionando jogabilidade com ainda mais realismo, a FIFA World Cup™ masculina e feminina que chegarão ao jogo em atualizações pós-lançamento, a inclusão de clubes femininos, recursos de crossplay* e muito mais. Sinta a autenticidade inigualável com mais de 19.000 atletas, mais de 700 clubes, 100 estádios e mais de 30 ligas no FIFA 23.', 357.99, 5.9, 4),
(8,
    'Apex Legends',
    'apex-legends',
    'https://www.youtube.com/embed/innmNewjkuk',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1172470/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1172470/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1172470/library_600x900_2x.jpg',
    '2019-02-04 00:00:00', 'Apex Legends é o premiado jogo de tiro em primeira pessoa de heróis e heroínas da Respawn Entertainment. Domine um elenco cada vez mais amplo de personagens lendários com habilidades poderosas, experimente partidas estratégicas e a jogabilidade inovadora na próxima evolução do jogo de tiro de heroísmo.', 0.0, 8.8, 4),
(9,
    'Sekiro™: Shadows Die Twice',
    'sekiro-shadows-die-twice',
    'https://www.youtube.com/embed/4OgoTZXPACo',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/814380/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/814380/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/814380/library_600x900_2x.jpg',
    '2019-03-21 00:00:00', 'Trilhe seu próprio caminho de vingança nesta premiada aventura da FromSoftware, os criadores de Bloodborne e da franquia Dark Souls. Obtenha vingança. Retome sua honra. Mate astuciosamente.', 199.90, 9.1, 1),
(10,
    'Resident Evil 4 (2023)',
    'resident-evil-4-2023',
    'https://www.youtube.com/embed/j5Xv2lM9wes',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/2050650/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/2050650/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/2050650/library_600x900_2x.jpg',
    '2023-03-23 00:00:00',
    'Sobrevivência é apenas o começo. Seis anos se passaram desde o desastre biológico em Raccoon City. Leon S. Kennedy, um dos sobreviventes, segue o rastro da raptada filha do presidente até uma vila europeia isolada, onde há algo terrivelmente errado com os habitantes.',
    249.00, 9.6, 6),
(11,
    'Blasphemous',
    'blasphemous',
    'https://www.youtube.com/embed/seGW4vdfL7A',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/774361/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/774361/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/774361/library_600x900_2x.jpg',
    '2019-09-10 00:00:00',
    'Blasphemous é um jogo de ação em plataforma brutal com um combate desafiador. Explore o mundo sinistro de Cvstodia, melhore suas habilidades e execute com violência as hordas de inimigos que se colocam entre você e sua peregrinação para interromper o ciclo de perdição eterna.',
    14.90, 8.9, 7),
(12,
    'Elden Ring',
    'elden-ring',
    'https://www.youtube.com/embed/E3Huy2cdih0',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1245620/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1245620/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1245620/library_600x900_2x.jpg',
    '2022-02-24 00:00:00',
    'O NOVO RPG DE AÇÃO E FANTASIA. Levante-se, Maculado, e seja guiado pela graça para portar o poder do Anel Prístino e se tornar um Lorde Prístino nas Terras Intermédias.',
    249.90, 9.9, 1),
(13,
    'Resident Evil 2 (2023)',
    'resident-evil-2-2023',
    'https://www.youtube.com/embed/u3wS-Q2KBpk',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/883710/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/883710/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/883710/library_600x900_2x.jpg',
    '2019-01-25 00:00:00',
    'Obra prima que definiu o gênero, Resident Evil 2 retorna, completamente refeito com uma experiência narrativa mais profunda. Usando o RE Engine de propriedade da Capcom, Resident Evil 2 oferece uma nova visão na clássica saga de horror de sobrevivência com visuais realistas de tirar o fôlego, áudio imersivo de acelerar o coração, uma nova câmera sobre o ombro e controles modernos além de modos de jogabilidade do jogo original.',
    139.90, 9.2, 6),
(14,
    'Resident Evil 3 (2023)',
    'resident-evil-3-2023',
    'https://www.youtube.com/embed/xNjGFUaorYc',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/952060/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/952060/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/952060/library_600x900_2x.jpg',
    '2020-04-03 00:00:00',
    'Jill Valentine é uma das últimas pessoas em Raccoon City a testemunhar as atrocidades que a Umbrella cometeu. Para pará-la a Umbrella libera sua arma secreta suprema: Nemesis! Também inclui a Resident Evil Resistance, um novo modo de jogo multijogador online 4x1.',
    129.90, 7.4, 6),
(15,
    'The Witcher® 3: Wild Hunt',
    'the-witcher-3-wild-hunt',
    'https://www.youtube.com/embed/c0i88t0Kacs',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/292030/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/292030/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/292030/library_600x900_2x.jpg',
    '2015-05-18 00:00:00',
    'Você é Geralt de Rívia, mercenário matador de monstros. Você está em um continente devastado pela guerra e infestado de monstros para você explorar à vontade. Sua tarefa é encontrar Ciri, a Criança da Profecia — uma arma viva que pode alterar a forma do mundo.',
    79.99, 9.5, 8),
(16,
   'Cyberpunk 2077',
   'cyberpunk-2077',
   'https://www.youtube.com/embed/qIcTM8WXFjk',
   'https://cdn.cloudflare.steamstatic.com/steam/apps/1091500/capsule_616x353.jpg',
   'https://cdn.cloudflare.steamstatic.com/steam/apps/1091500/library_hero.jpg',
   'https://cdn.cloudflare.steamstatic.com/steam/apps/1091500/library_600x900_2x.jpg',
   '2020-12-09 00:00:00',
   'Cyberpunk 2077 é um RPG de ação e aventura em mundo aberto que se passa em Night City, uma megalópole perigosa onde todos são obcecados por poder, glamour e alterações corporais.',
    199.99, 6.9, 8),
(17,
    'Resident Evil 7 Biohazard',
    'resident-evil-7-biohazard',
    'https://www.youtube.com/embed/RgYqQsbKn6w',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/418370/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/418370/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/418370/library_600x900_2x.jpg',
    '2017-01-24 00:00:00',
    'Medo e isolamento se infiltram nas paredes de uma casa de campo abandonada. "7" marca um novo início para o horror de sobrevivência com a “Visão Isolada” da visceral perspectiva em primeira pessoa.',
    66.90, 8.9, 6),
(18,
    'Resident Evil Village',
    'resident-evil-village',
    'https://www.youtube.com/embed/arEdruKxrQ8',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1196590/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1196590/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/1196590/library_600x900_2x.jpg',
    '2021-05-07 00:00:00',
    'Vivencie o horror de sobrevivência como nunca na 8ª sequência parte da franquia Resident Evil - Resident Evil Village. Com gráficos detalhados, ação intensa em primeira pessoa e uma narrativa primorosa, o terror nunca foi tão realista.',
    139.90, 8.5, 6),
(19,
   'Forza Horizon 5',
   'forza-horizon-5',
   'https://www.youtube.com/embed/agI0xMBQo2U',
   'https://cdn.cloudflare.steamstatic.com/steam/apps/1551360/capsule_616x353.jpg',
   'https://cdn.cloudflare.steamstatic.com/steam/apps/1551360/library_hero.jpg',
   'https://cdn.cloudflare.steamstatic.com/steam/apps/1551360/library_600x900_2x.jpg',
   '2021-11-09 00:00:00',
   'Explore um mundo aberto vibrante nas terras mexicanas com corridas divertidas e sem limites enquanto pilota os melhores carros do mundo. Acelere para o Parque Hot Wheels e corra nas pistas mais radicais já criadas. Requer Forza Horizon 5, expansão vendida separadamente.',
   239.90, 8.2, 9),
(20,
    'Mortal Kombat 11',
    'mortal-kombat-11',
    'https://www.youtube.com/embed/H3lfTa3JCek',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/976310/capsule_616x353.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/976310/library_hero.jpg',
    'https://cdn.cloudflare.steamstatic.com/steam/apps/976310/library_600x900_2x.jpg',
    '2021-11-09 00:00:00',
    'Mortal Kombat está de volta, melhor do que nunca, em uma evolução da icônica franquia.',
    229.99, 8.7, 10);

insert into requisito (id, jogo_id, tipo, observacoes, sistema, memoria, processador, placa_de_video, armazenamento) values
(1, 1, 'MINIMOS', 'Low Settings, 60 FPS @ 1080p', 'Windows 7 64-bit', '6 GB de RAM', 'Intel Core i5-2300 2.8 GHz / AMD FX-6300, 3.5 GHz', 'GeForce GTX 460, 1 GB / Radeon HD 6870, 1 GB', '8 GB de espaço disponível'),
(2, 1, 'RECOMENDADOS', 'Ultra Settings, 60 FPS @ 1080p', 'Windows 10 64-bit', '8 GB de RAM', 'Intel Core i5-4570 3.2 GHz / AMD FX-8350 4.2 GHz', 'GeForce GTX 660, 2 GB / Radeon HD 7870, 2 GB', '8 GB de espaço disponível'),

(4, 2, 'MINIMOS', 'Low Settings, 60 FPS @ 1080p', 'Windows 7 64-bit', '4 GB de RAM', 'AMD® A8 3870 3,6 Ghz or Intel® Core ™ i3 2100 3.1Ghz', 'NVIDIA® GeForce GTX 465 / ATI Radeon TM HD 6870', '23 GB de espaço disponível'),
(5, 2, 'RECOMENDADOS', 'Ultra Settings, 60 FPS @ 1080p', 'Windows 10 64-bit', '8 GB de RAM', 'AMD® FX 8150 3.6 GHz or Intel® Core™ i7 2600 3.4 GHz', 'NVIDIA® GeForce® GTX 750, ATI Radeon™ HD 7850', '23 GB de espaço disponível'),

(7, 3, 'MINIMOS', '60 FPS @ 720p, Low', 'Windows 7 64-bit', '4 GB de RAM', 'Intel Core i3-2100 / AMD® FX-6300', 'NVIDIA® GeForce GTX 750 Ti / ATI Radeon HD 7950', '25 GB de espaço disponível'),
(8, 3, 'RECOMENDADOS', '60 FPS @ 1080p, Ultra', 'Windows 10 64-bit', '8 GB de RAM', 'Intel Core i7-3770 / AMD® FX-8350', 'NVIDIA® GeForce GTX 970 / ATI Radeon R9 series', '25 GB de espaço disponível'),

(10, 4, 'MINIMOS', null, 'Windows® 7/Vista/XP', '2 GB de RAM', 'Intel® Core™ 2 Duo E6600 ou AMD Phenom™ X3 8750', 'A placa de vídeo precisa ter 256 MB ou mais de memória e ser compatível com DirectX 9 e Pixel Shader 3.0', '15 GB de espaço disponível');

insert into categoria (id, nome) values
(1, 'FPS'),
(2, 'RPG'),
(3, 'Soulslike'),
(4, 'Simulador'),
(5, 'Ação'),
(6, 'Aventura'),
(7, 'Tiro'),
(8, 'Esportes'),
(9, 'Metroidvania'),
(10, 'Difícil'),
(11, 'Indie'),
(12, 'Terror'),
(13, 'Corrida'),
(14, 'Luta');

insert into plataforma (id, nome) values
(1, 'PC'), (2, 'PS4'), (3, 'PS5'), (4, 'Xbox ONE'), (5, 'Xbox Series X/S'), (6, 'Nintendo Switch');

insert into modo (id, nome) values
(1, 'Um jogador'), (2, 'Multijogador'), (3, 'PvP online'), (4, 'PvE online'), (5, 'Cooperativo on-line');

insert into forma_pagamento (id, nome) values
(1, 'Pix'), (2, 'Boleto'), (3, 'Cartão de crédito'), (4, 'Cartão de débito');

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
(10, 1),
(11, 1),
(12, 1), (12, 3), (12, 4), (12, 5),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 1), (19, 2), (19, 3), (19, 5),
(20, 1), (20, 2), (20, 3);

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
(10, 5), (10, 7), (10, 12),
(11, 3), (11, 9), (11, 11),
(12, 2), (12, 3), (12, 6),
(13, 5), (13, 7), (13, 12),
(14, 5), (14, 7), (14, 12),
(15, 2), (15, 5), (15, 6),
(16, 2), (16, 5), (16, 6), (16, 7),
(17, 7), (17, 12),
(18, 4), (18, 7), (18, 12),
(19, 4), (19, 6), (19, 13),
(20, 5), (20, 14);

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
(10, 1), (10, 2), (10, 3), (10, 4), (10, 5),
(11, 1), (11, 3), (11, 5),
(12, 1), (12, 2), (12, 3), (12, 4), (12, 5),
(13, 1), (13 ,2), (13, 3), (13, 4), (13, 5),
(14, 1), (14, 2), (14, 3), (14, 4), (14, 5),
(15, 1), (15, 2), (15, 4),
(16, 1), (16, 2), (16, 3), (16, 4), (16, 5),
(17, 1), (17, 2), (17, 3), (17, 4), (17, 5),
(18, 1), (18, 2), (18, 3), (18, 4), (18, 5),
(19, 1), (19, 4), (19, 5),
(20, 1), (20, 2), (20, 3), (20, 4), (20, 5);

insert into usuario (id, nome, username, avatar_url, tipo, email, senha, data_cadastro) values
(1, "Gabriel", "opaco", "https://api.dicebear.com/6.x/bottts/svg?seed=opaco9c6595fa-d06b-4714-88be-e56d76b03531", "ADMIN", "gabriel.opaco@email.com", "$2a$12$Z7eR/rUV9CusNU3IEDtyhOhmos/sAQOv5W7MuEd/tU9lejmCdmzZ2", utc_timestamp),
(2, "Usuário", "usuario", "https://api.dicebear.com/6.x/bottts/svg?seed=usuario1fc8957d-c3a5-4482-92d1-b391ff4bd743", "USER", "usuario.gamer@email.com", "$2a$12$Z7eR/rUV9CusNU3IEDtyhOhmos/sAQOv5W7MuEd/tU9lejmCdmzZ2", utc_timestamp);

insert into pedido (id, codigo, valor_total, data_criacao, data_confirmacao, data_cancelamento, data_reembolso, status, usuario_id, forma_pagamento_id) values
(1, "7221549c-db06-11ed-afa1-0242ac120002", 389.79, utc_timestamp, utc_timestamp, null, null, "CONFIRMADO", 1, 3),
(2, "b751b070-db06-11ed-afa1-0242ac120002", 79.90, utc_timestamp, null, utc_timestamp, null, "CANCELADO", 1, 2),
(3, "d5e719f8-db06-11ed-afa1-0242ac120002", 259.89, utc_timestamp, utc_timestamp, null, null, "CONFIRMADO", 2, 1),
(4, "355729bc-2438-11ee-be56-0242ac120002", 257.90, utc_timestamp, null,null, null, "CRIADO", 2, 4);

insert into pedido_jogo (pedido_id, jogo_id) values
(1, 1), (1, 3),
(2, 4),
(3, 3),
(4, 6);

insert into compra (id, usuario_id, jogo_id, codigo_pedido, data_compra, chave_ativacao) values
(1, 1, 1, "7221549c-db06-11ed-afa1-0242ac120002", utc_timestamp, "29EC1-47F94-66CW2"),
(2, 1, 3, "7221549c-db06-11ed-afa1-0242ac120002", utc_timestamp, "88257-C6D62-7E6DG"),
(3, 2, 3, "d5e719f8-db06-11ed-afa1-0242ac120002", utc_timestamp, "65A28-4E3B3-E44AC");

insert into usuario_desejos (usuario_id, jogo_id) values
(1, 4), (1, 2), (1, 19),
(2, 9), (2, 10);




