package com.bestsellers.assignment.gamersdirectory.service;

import com.bestsellers.assignment.gamersdirectory.entity.RegisteredGamesView;
import com.bestsellers.assignment.gamersdirectory.model.ResponseDTO;
import com.bestsellers.assignment.gamersdirectory.model.SearchRequestDTO;
import com.bestsellers.assignment.gamersdirectory.model.SearchResponseDTO;

import java.util.List;

public interface GamersDirectoryService {

    ResponseDTO findPlayersByGamesWithLevel(String level);

    List<SearchResponseDTO> findByCriteria(SearchRequestDTO searchRequestDTO);

}
