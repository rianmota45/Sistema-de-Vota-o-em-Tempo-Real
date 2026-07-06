package org.example.sistemadevotacaoemtemporeal.Controller;

import DTO.UserDTO.UserRequestDTO;
import DTO.UserDTO.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.example.sistemadevotacaoemtemporeal.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {

        return ResponseEntity.ok(userService.createUser(userRequestDTO));
    }

    @GetMapping("find-user/{uuid}")
    public ResponseEntity<?> findUserByID(@PathVariable UUID uuid) {

        return ResponseEntity.ok(userService.findByUuid(uuid));
    }

    @DeleteMapping("delete-user/{userID}")
    public ResponseEntity<String> deleteUserByID(@PathVariable("userID") UUID userID) {

        return ResponseEntity.ok(userService.deleteByUuid(userID));
    }
}