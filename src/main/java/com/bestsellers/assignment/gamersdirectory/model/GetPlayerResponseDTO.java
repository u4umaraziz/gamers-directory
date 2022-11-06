package com.bestsellers.assignment.gamersdirectory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
@Data
public class GetPlayerResponseDTO {
    private String gamerName;
    private String location;
    private String gameLevel;
}