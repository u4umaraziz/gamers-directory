package com.bestsellers.assignment.gamersdirectory.controller;

import com.bestsellers.assignment.gamersdirectory.entity.Player;
import com.bestsellers.assignment.gamersdirectory.model.PlayerRequestDTO;
import com.bestsellers.assignment.gamersdirectory.model.LinkGameRequestDTO;
import com.bestsellers.assignment.gamersdirectory.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController ("/api")
@RequiredArgsConstructor
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @ApiOperation(value = "Service to register new Player", nickname = "registerGamer")
    @PostMapping("/register")
    public ResponseEntity<String> register(final @Valid @RequestBody PlayerRequestDTO gamerRequestDTO) {
        log.debug("Registering new player: {}", gamerRequestDTO.toString());

        String id = playerService.registerPlayer(gamerRequestDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(id);

    }

    @ApiOperation(value = "Service to get all Players")
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllGamers() {
        log.debug("Getting all players .....");
        return  ResponseEntity.ok(playerService.getAllPlayers());

    }

    @ApiOperation(value = "Service to link player to specific game")
    @PostMapping("/link")
    public ResponseEntity<Void> linkGame(final @Valid @RequestBody LinkGameRequestDTO linkGameRequestDTO) {
        log.debug("Linking game ....");
        playerService.linkGame(linkGameRequestDTO);
        return  ResponseEntity.status(HttpStatus.OK).build();

    }
}
