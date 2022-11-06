INSERT INTO PLAYER (id, player_id, name, location) VALUES (1, 'a08210c2-c6a3-4b00-a0e1-9f84cdef24d0', 'Gamer1', 'Denmark');
INSERT INTO PLAYER (id, player_id, name, location) VALUES (2, 'b08210c2-c6a3-4b00-a0e1-9f84cdef24d0', 'Gamer2', 'Pakistan');
INSERT INTO PLAYER (id, player_id, name, location) VALUES (3, 'c08210c2-c6a3-4b00-a0e1-9f84cdef24d0', 'Gamer4', 'United Kingdom');
INSERT INTO PLAYER (id, player_id, name, location) VALUES (4, 'd08210c2-c6a3-4b00-a0e1-9f84cdef24d0', 'Gamer5', 'India');
INSERT INTO PLAYER (id, player_id, name, location) VALUES (5, 'e08210c2-c6a3-4b00-a0e1-9f84cdef24d0', 'Gamer6', 'Denmark');
INSERT INTO PLAYER (id, player_id, name, location) VALUES (6, 'f08210c2-c6a3-4b00-a0e1-9f84cdef24d0', 'Gamer7', 'Denmark');
INSERT INTO PLAYER (id, player_id, name, location) VALUES (7, 'g08210c2-c6a3-4b00-a0e1-9f84cdef24d0', 'Gamer8', 'United Kingdom');
INSERT INTO PLAYER (id, player_id, name, location) VALUES (8, 'h08210c2-c6a3-4b00-a0e1-9f84cdef24d0', 'Gamer9', 'Pakistan');
INSERT INTO PLAYER (id, player_id, name, location) VALUES (9, 'i08210c2-c6a3-4b00-a0e1-9f84cdef24d0', 'Gamer10', 'India');


INSERT INTO GAMES (id, game_id, name) VALUES (1, 'a08210c2-c6a3-4b00-a0e1-9f84cdef24d1', 'Call of Duty');
INSERT INTO GAMES (id, game_id, name) VALUES (2, 'b08210c2-c6a3-4b00-a0e1-9f84cdef24d2', 'Resident Evil');
INSERT INTO GAMES (id, game_id, name) VALUES (3, 'c08210c2-c6a3-4b00-a0e1-9f84cdef24d3', 'Fortinte');
INSERT INTO GAMES (id, game_id, name) VALUES (4, 'd08210c2-c6a3-4b00-a0e1-9f84cdef24d4', 'GTA 5');
INSERT INTO GAMES (id, game_id, name) VALUES (5, 'e08210c2-c6a3-4b00-a0e1-9f84cdef24d5', 'Tekken 7');


INSERT INTO GAME_MAPPING (id, game_id, player_id, gamer_level) VALUES (1, 1, 1, 'INVINCIBLE');
INSERT INTO GAME_MAPPING (id, game_id, player_id, gamer_level) VALUES (2, 1, 2, 'NOOB');
INSERT INTO GAME_MAPPING (id, game_id, player_id, gamer_level) VALUES (3, 1, 3, 'INVINCIBLE');
INSERT INTO GAME_MAPPING (id, game_id, player_id, gamer_level) VALUES (4, 2, 1, 'INVINCIBLE');
INSERT INTO GAME_MAPPING (id, game_id, player_id, gamer_level) VALUES (5, 2, 7, 'PRO');


--ALTER VIEW REGISTERED_GAMES_VIEW AS
--SELECT g1.name as "Gamer Name", g1.location, g2.name as "Game Name", gm.gamer_level as "LEVEL"
--FROM GAMER g1
--         INNER JOIN GAME_MAPPING gm ON g1.id = gm.gamer_id
--         INNER JOIN GAMES g2 ON g2.id = gm.game_id