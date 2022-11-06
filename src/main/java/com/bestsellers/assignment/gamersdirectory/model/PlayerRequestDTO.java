package com.bestsellers.assignment.gamersdirectory.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
public class PlayerRequestDTO {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Location cannot be null")
    private String location;

    private String level;
    private String game;

    @Override
    public String toString() {
        return "PlayerRequestDTO{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", level='" + level + '\'' +
                ", game='" + game + '\'' +
                '}';
    }
}
