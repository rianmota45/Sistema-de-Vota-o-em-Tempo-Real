package org.example.sistemadevotacaoemtemporeal.Controller;

import DTO.PoolDTO.PoolRequestDTO;
import DTO.PoolDTO.PoolResponseDTO;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.VoteRequest;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.VoteResponse;
import org.example.sistemadevotacaoemtemporeal.Service.PoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/")
@RequiredArgsConstructor
public class PoolController {

    @Autowired
    private final PoolService poolService;

    @PostMapping("/create-pool/{userid}")
    public ResponseEntity<PoolResponseDTO> createPool(@RequestBody PoolRequestDTO poolRequestDTO, @PathVariable UUID userid) {

        return ResponseEntity.ok(poolService.createPool(poolRequestDTO, userid));
    }

    @GetMapping("/get-pool=/{poolid}/{userid}")
    public ResponseEntity<PoolResponseDTO> getPool(@PathVariable Integer poolid, @PathVariable UUID userid){

        return ResponseEntity.ok(poolService.getPool(poolid, userid));
    }

    @PostMapping("/vote/{userid}/{poolid}/{optionid}")
    public VoteResponse vote(
            @PathVariable UUID userid,
            @PathVariable Integer poolid,
            @PathVariable Integer optionid
    ) {
        return poolService.vote(new VoteRequest(userid, poolid, optionid));
    }
}
