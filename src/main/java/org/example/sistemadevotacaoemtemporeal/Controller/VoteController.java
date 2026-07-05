package org.example.sistemadevotacaoemtemporeal.Controller;

import dto.user_dto.UserRequestDTO;
import dto.user_dto.UserResponseDTO;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.PoolEntity;
import org.example.sistemadevotacaoemtemporeal.Service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// por enquanto tá tudo no mesmo controller, mas eu vou mudar isso depois
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VoteController {

    @Autowired
    private final VoteService voteService;

    @PostMapping("/create-user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){

        return ResponseEntity.ok(voteService.createUser(userRequestDTO));
    }

    @GetMapping("/test")
    public String test(){
        return "Tudo funcionando!!!";
    }
    @PostMapping("/test-post")
    public String testPost(){
        return "Tudo funcionando no post!!!";
    }

    @PostMapping("/create-pool")
    public ResponseEntity<PoolEntity> createPool(){
        return ResponseEntity.ok(voteService.createPool());
    }

    @GetMapping("/get-all-pools")
    public ResponseEntity<List<PoolEntity>> getAllPools(){
        return ResponseEntity.ok(voteService.getAllPools());
    }

    @PostMapping("/vote-pid={id}-op={opid}-usr={usrId}")
    public String vote(
            @PathVariable Integer id,
            @PathVariable Integer opid,
            @PathVariable UUID usrId
            ){
        return voteService.voteIN(id, opid, usrId);
    }
}
