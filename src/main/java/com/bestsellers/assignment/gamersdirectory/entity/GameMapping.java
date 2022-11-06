package com.bestsellers.assignment.gamersdirectory.entity;

import com.bestsellers.assignment.gamersdirectory.enums.GamerLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_mapping" , uniqueConstraints = {
@UniqueConstraint(name = "UniquePlayerAndGameId", columnNames = {"player_id", "game_id"})})
public class GameMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Games games;

    @Enumerated(EnumType.STRING)
    @Column(name = "gamer_level")
    private GamerLevelEnum gamerLevel;
}
