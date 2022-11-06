package com.bestsellers.assignment.gamersdirectory.model;

import com.bestsellers.assignment.gamersdirectory.enums.GamerLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class LinkGameRequestDTO {

    @NotNull(message = "Game ID cannot be null")
    private String gameId;

    @NotNull(message = "Gamer ID cannot be null")
    private String playerId;

    private GamerLevelEnum level;

    @Override
    public String toString() {
        return "LinkGameRequestDTO{" +
                "gameId='" + gameId + '\'' +
                ", playerId='" + playerId + '\'' +
                ", level=" + level +
                '}';
    }
}
