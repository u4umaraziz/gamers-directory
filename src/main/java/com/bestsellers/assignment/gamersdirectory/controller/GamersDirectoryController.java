package com.bestsellers.assignment.gamersdirectory.controller;

import com.bestsellers.assignment.gamersdirectory.model.ResponseDTO;
import com.bestsellers.assignment.gamersdirectory.model.SearchRequestDTO;
import com.bestsellers.assignment.gamersdirectory.model.SearchResponseDTO;
import com.bestsellers.assignment.gamersdirectory.service.GamersDirectoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController ("/api/games")
@RequiredArgsConstructor
public class GamersDirectoryController {

    @Autowired
    private GamersDirectoryService gamersDirectoryService;

    @ApiOperation(value = "Service to filter games and get players on the basis of level ")
    @GetMapping("/find/{level}")
    public ResponseEntity<ResponseDTO> findByLevel(@PathVariable(name = "level")String level) {
        return ResponseEntity.ok(gamersDirectoryService.findPlayersByGamesWithLevel(level));
    }

    @ApiOperation(value = "Service to get players on the basis of search criteria")
    @PostMapping("/search")
    public ResponseEntity<List<SearchResponseDTO>> findByCriteria(@RequestBody SearchRequestDTO searchRequestDTO) {
        return ResponseEntity.ok(gamersDirectoryService.findByCriteria(searchRequestDTO));
    }

}
