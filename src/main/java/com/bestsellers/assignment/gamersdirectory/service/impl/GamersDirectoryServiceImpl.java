package com.bestsellers.assignment.gamersdirectory.service.impl;

import com.bestsellers.assignment.gamersdirectory.entity.RegisteredGamesView;
import com.bestsellers.assignment.gamersdirectory.model.GetPlayerResponseDTO;
import com.bestsellers.assignment.gamersdirectory.model.ResponseDTO;
import com.bestsellers.assignment.gamersdirectory.model.SearchRequestDTO;
import com.bestsellers.assignment.gamersdirectory.model.SearchResponseDTO;
import com.bestsellers.assignment.gamersdirectory.repository.RegisterdGamesViewRepository;
import com.bestsellers.assignment.gamersdirectory.service.GamersDirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamersDirectoryServiceImpl implements GamersDirectoryService {

    @Autowired
    private RegisterdGamesViewRepository registerdGamesViewRepository;

    @Override
    public ResponseDTO findPlayersByGamesWithLevel(final String level) {
        List<RegisteredGamesView> registeredGamesViewList =
                registerdGamesViewRepository.findRegisteredGamesViewByGameLevel(level);

        Map<String, List<GetPlayerResponseDTO>> responseMap = new HashMap<>();
        registeredGamesViewList.stream().forEach(registeredGamesView -> {
            if (Objects.isNull(responseMap.get(registeredGamesView.getGameName()))) {

                List<GetPlayerResponseDTO> getGamersResponseDTOList = new ArrayList<>();
                GetPlayerResponseDTO getGamersResponseDTO = new GetPlayerResponseDTO().toBuilder()
                        .gameLevel(registeredGamesView.getGameLevel())
                        .gamerName(registeredGamesView.getPlayerName())
                        .location(registeredGamesView.getLocation())
                        .build();
                getGamersResponseDTOList.add(getGamersResponseDTO);

                responseMap.put(registeredGamesView.getGameName(), getGamersResponseDTOList);
            } else {
                List<GetPlayerResponseDTO> gamersResponseDTOS = responseMap.get(registeredGamesView.getGameName());
                GetPlayerResponseDTO getGamersResponseDTO = new GetPlayerResponseDTO().toBuilder()
                        .gameLevel(registeredGamesView.getGameLevel())
                        .gamerName(registeredGamesView.getPlayerName())
                        .location(registeredGamesView.getLocation())
                        .build();
                gamersResponseDTOS.add(getGamersResponseDTO);

                responseMap.put(registeredGamesView.getGameName(), gamersResponseDTOS);
            }
        });


        return new ResponseDTO().toBuilder()
                .gamesList(responseMap)
                .build();
    }

    @Override
    public List<SearchResponseDTO> findByCriteria (final SearchRequestDTO searchRequestDTO){

        final ExampleMatcher SEARCH_CONDITIONS_MATCH_ALL = ExampleMatcher
                .matching()
                .withMatcher("location", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("gameName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("gameLevel", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());

        final ExampleMatcher SEARCH_CONDITIONS_MATCH_ANY = ExampleMatcher
                .matchingAny()
                .withMatcher("location", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("gameName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("gameLevel", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());

        Pageable pageable = PageRequest.of(0, 100);

        RegisteredGamesView registeredGamesView = new RegisteredGamesView().toBuilder()
                .gameLevel(Objects.nonNull(searchRequestDTO.getGameLevel()) ? searchRequestDTO.getGameLevel().name() : null)
                .location(searchRequestDTO.getLocation())
                .gameName(searchRequestDTO.getGameName())
                .build();

        Example<RegisteredGamesView> example = Example.of(registeredGamesView, searchRequestDTO.isMatchAll()
                ? SEARCH_CONDITIONS_MATCH_ALL : SEARCH_CONDITIONS_MATCH_ANY);

        Page<RegisteredGamesView> registeredGamesViews = registerdGamesViewRepository.findAll(example, pageable);

        return registeredGamesViews.stream()
                .map(this::map)
                .collect(Collectors.toList());


    }

    private SearchResponseDTO map(final RegisteredGamesView record) {
        final SearchResponseDTO searchResponseDTO = new SearchResponseDTO();

        searchResponseDTO.setGameName(record.getGameName());
        searchResponseDTO.setGameLevel(record.getGameLevel());
        searchResponseDTO.setPlayerName(record.getPlayerName());
        searchResponseDTO.setLocation(record.getLocation());

        return searchResponseDTO;
    }

}
