CREATE TABLE player
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    player_id VARCHAR(255)          NOT NULL,
    name      VARCHAR(255)          NOT NULL,
    location  VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_gamer PRIMARY KEY (id)
);

CREATE TABLE games
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    game_id VARCHAR(255)          NOT NULL,
    name    VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_games PRIMARY KEY (id)
);

CREATE TABLE game_mapping
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    player_id   BIGINT                NOT NULL,
    game_id     BIGINT                NOT NULL,
    gamer_level VARCHAR(255)          NULL,
    CONSTRAINT pk_game_mapping PRIMARY KEY (id)
);

ALTER TABLE game_mapping
    ADD CONSTRAINT UniqueGamerAndGameId UNIQUE (player_id, game_id);

ALTER TABLE game_mapping
    ADD CONSTRAINT FK_GAME_MAPPING_ON_GAME FOREIGN KEY (game_id) REFERENCES games (id);

ALTER TABLE game_mapping
    ADD CONSTRAINT FK_GAME_MAPPING_ON_GAMER FOREIGN KEY (player_id) REFERENCES player (id);

CREATE VIEW REGISTERED_GAMES_VIEW AS
SELECT player.id , player.name as "PLAYER_NAME", player.location, game.name as "GAME_NAME", gm.gamer_level
FROM PLAYER player
         INNER JOIN GAME_MAPPING gm ON player.id = gm.player_id
         INNER JOIN GAMES game ON game.id = gm.game_id