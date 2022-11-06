package com.bestsellers.assignment.gamersdirectory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
@Data
public class ResponseDTO {

    Map<String, List<GetPlayerResponseDTO>> gamesList = new HashMap<>();

}