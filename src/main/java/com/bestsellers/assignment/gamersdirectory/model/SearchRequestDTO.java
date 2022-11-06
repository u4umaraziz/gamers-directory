package com.bestsellers.assignment.gamersdirectory.model;

import com.bestsellers.assignment.gamersdirectory.enums.GamerLevelEnum;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class SearchRequestDTO {

    private String playerName;
    private String gameName;
    private String location;
    private GamerLevelEnum gameLevel;
    private boolean matchAll;

}
