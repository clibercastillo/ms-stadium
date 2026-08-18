package com.utp.ms_stadium.controller;

import com.utp.ms_stadium.dto.*;
import com.utp.ms_stadium.service.StadiumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stadiums")
@RequiredArgsConstructor
@Tag(name = "Stadiums", description = "Gestión de canchas sintéticas")
public class StadiumController {

    private final StadiumService stadiumService;

    @PostMapping
    @Operation(summary = "Registrar nueva cancha (requiere token)")
    public ResponseEntity<StadiumResponse> create(@Valid @RequestBody StadiumRequest request) {
        return ResponseEntity.ok(stadiumService.create(request));
    }

    @GetMapping
    @Operation(summary = "Listar todas las canchas")
    public ResponseEntity<List<StadiumResponse>> findAll() {
        return ResponseEntity.ok(stadiumService.findAll());
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Buscar canchas por ciudad")
    public ResponseEntity<List<StadiumResponse>> findByCity(@PathVariable String city) {
        return ResponseEntity.ok(stadiumService.findByCity(city));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cancha por id")
    public ResponseEntity<StadiumResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stadiumService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cancha (requiere token)")
    public ResponseEntity<StadiumResponse> update(@PathVariable Long id, @Valid @RequestBody StadiumRequest request) {
        return ResponseEntity.ok(stadiumService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cancha (requiere token)")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stadiumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}