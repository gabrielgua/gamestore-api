set foreign_key_checks = 0;

delete from jogo;
delete from categoria;
delete from plataforma;
delete from jogo_categoria;
delete from jogo_plataforma;

set foreign_key_checks = 1;


alter table jogo auto_increment = 1;
alter table categoria auto_increment = 1;
alter table plataforma auto_increment = 1;

insert into jogo (id, nome, descricao, preco, nota) values
(1, 'Dark Souls I', 'Bate e rola 1.', 129.90, 9.8),
(2, 'Dark Souls II', 'Bom jogo, porém os únicos bosses bons são os das DLCs.', 179.90, 7.8),
(3, 'Dark Souls III', 'Bate e rola 3, boss fights mais cinematográficas da franquia SOULS', 259.89, 9.2),
(4, 'Counter-Strike:Global Offensive', 'Pai dos jogos de tiro.', 79.90, 8.9),
(5, 'Counter Strike 2', 'Por favor Valve me deixa testar.', 0.0, 8.8);

insert into categoria (id, nome) values
(1, 'FPS'), (2, 'RPG'), (3, 'Souls-Like'), (4, 'Simulador'), (5, 'Ação');

insert into plataforma (id, nome) values
(1, 'PC'), (2, 'PS4'), (3, 'PS5'), (4, 'Xbox ONE'), (5, 'Xbox Series X/S');

insert into jogo_categoria (jogo_id, categoria_id) values
(1, 2), (1, 3),
(2, 2), (2, 3),
(3, 2), (3, 3),
(4, 1),
(5, 1);

insert into jogo_plataforma (jogo_id, plataforma_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5),
(4, 1),
(5, 1);


