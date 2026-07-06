package org.example.sistemadevotacaoemtemporeal.Controller;

import DTO.PoolDTO.PoolRequestDTO;
import DTO.PoolDTO.PoolResponseDTO;
import org.example.sistemadevotacaoemtemporeal.Infrastructure.Entity.Pool.PoolEntity;
import org.example.sistemadevotacaoemtemporeal.Service.PoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/")
@RequiredArgsConstructor
public class PoolController {

    @Autowired
    private final PoolService voteService;

    @PostMapping("/create-pool")
    public ResponseEntity<PoolResponseDTO> createPool(@RequestBody PoolRequestDTO poolRequestDTO) {

        return ResponseEntity.ok(voteService.createPool(poolRequestDTO));
    }

    @GetMapping("/get-all-pools")
    public ResponseEntity<List<PoolEntity>> getAllPools() {

        return ResponseEntity.ok(voteService.getAllPools());
    }

    @PostMapping("/vote-pid={id}-op={opid}-usr={usrId}")
    public String vote(
            @PathVariable Integer id,
            @PathVariable Integer opid,
            @PathVariable UUID usrId
    ) {
        return voteService.voteIN(id, opid, usrId);
    }
}
