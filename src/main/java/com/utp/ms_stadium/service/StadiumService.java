package com.utp.ms_stadium.service;

import com.utp.ms_stadium.dto.*;
import com.utp.ms_stadium.entity.Stadium;
import com.utp.ms_stadium.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    public StadiumResponse create(StadiumRequest request) {
        String ownerEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Stadium stadium = Stadium.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .capacity(request.getCapacity())
                .fieldType(request.getFieldType())
                .pricePerHour(request.getPricePerHour())
                .ownerEmail(ownerEmail)
                .enabled(true)
                .build();

        return toResponse(stadiumRepository.save(stadium));
    }

    public List<StadiumResponse> findAll() {
        return stadiumRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<StadiumResponse> findByCity(String city) {
        return stadiumRepository.findByCityIgnoreCase(city).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public StadiumResponse findById(Long id) {
        Stadium stadium = stadiumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cancha no encontrada"));
        return toResponse(stadium);
    }

    public StadiumResponse update(Long id, StadiumRequest request) {
        Stadium stadium = stadiumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cancha no encontrada"));

        stadium.setName(request.getName());
        stadium.setAddress(request.getAddress());
        stadium.setCity(request.getCity());
        stadium.setCapacity(request.getCapacity());
        stadium.setFieldType(request.getFieldType());
        stadium.setPricePerHour(request.getPricePerHour());

        return toResponse(stadiumRepository.save(stadium));
    }

    public void delete(Long id) {
        stadiumRepository.deleteById(id);
    }

    private StadiumResponse toResponse(Stadium s) {
        return new StadiumResponse(
                s.getId(), s.getName(), s.getAddress(), s.getCity(),
                s.getCapacity(), s.getFieldType(), s.getPricePerHour(),
                s.getOwnerEmail(), s.isEnabled()
        );
    }
}