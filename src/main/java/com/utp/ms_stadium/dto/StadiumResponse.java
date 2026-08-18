package com.utp.ms_stadium.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StadiumResponse {
    private Long id;
    private String name;
    private String address;
    private String city;
    private Integer capacity;
    private String fieldType;
    private Double pricePerHour;
    private String ownerEmail;
    private boolean enabled;
}