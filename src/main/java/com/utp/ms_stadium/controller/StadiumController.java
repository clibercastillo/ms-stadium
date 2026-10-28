package com.utp.ms_stadium.controller;

import com.utp.ms_stadium.dto.*;
import com.utp.ms_stadium.service.StadiumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/stadiums")
@RequiredArgsConstructor
@Tag(name = "Stadiums", description = "Gestión de canchas sintéticas")
public class StadiumController {

    private final StadiumService stadiumService;

    @PostMapping
    @Operation(summary = "Registrar nueva cancha (requiere token)")
    public ResponseEntity<StadiumResponse> create(@Valid @RequestBody StadiumRequest request) {
        log.info("POST /api/stadiums - creating stadium: name={}, city={}, ownerEmail={}", request.getName(), request.getCity(), request.getOwnerEmail());
        StadiumResponse response = stadiumService.create(request);
        log.info("Stadium created successfully with id={}", response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar todas las canchas")
    public ResponseEntity<List<StadiumResponse>> findAll() {
        log.info("GET /api/stadiums - fetching all stadiums");
        List<StadiumResponse> stadiums = stadiumService.findAll();
        log.info("Total stadiums returned: {}", stadiums.size());
        return ResponseEntity.ok(stadiums);
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Buscar canchas por ciudad")
    public ResponseEntity<List<StadiumResponse>> findByCity(@PathVariable String city) {
        log.info("GET /api/stadiums/city/{} - searching stadiums by city", city);
        List<StadiumResponse> stadiums = stadiumService.findByCity(city);
        log.info("Found {} stadiums for city {}", stadiums.size(), city);
        return ResponseEntity.ok(stadiums);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cancha por id")
    public ResponseEntity<StadiumResponse> findById(@PathVariable Long id) {
        log.info("GET /api/stadiums/{} - fetching stadium by id", id);
        StadiumResponse response = stadiumService.findById(id);
        log.info("Stadium loaded successfully with id={}", id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cancha (requiere token)")
    public ResponseEntity<StadiumResponse> update(@PathVariable Long id, @Valid @RequestBody StadiumRequest request) {
        log.info("PUT /api/stadiums/{} - updating stadium: name={}, city={}", id, request.getName(), request.getCity());
        StadiumResponse response = stadiumService.update(id, request);
        log.info("Stadium updated successfully with id={}", id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cancha (requiere token)")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("DELETE /api/stadiums/{} - deleting stadium", id);
        stadiumService.delete(id);
        log.info("Stadium deleted successfully with id={}", id);
        return ResponseEntity.noContent().build();
    }
}
