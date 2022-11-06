package com.bestsellers.assignment.gamersdirectory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
@Data
public class GamesResponseDTO {

    private String gameName;
    private List<GetPlayerResponseDTO> gamersList;

}