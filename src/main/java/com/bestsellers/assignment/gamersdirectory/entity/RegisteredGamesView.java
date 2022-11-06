package com.bestsellers.assignment.gamersdirectory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@Immutable
public class RegisteredGamesView {

    @Id
    @Column(name = "id")
    private Long gamerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "game_name")
    private String gameName;

    private String location;

    @Column(name = "gamer_level")
    private String gameLevel;

}