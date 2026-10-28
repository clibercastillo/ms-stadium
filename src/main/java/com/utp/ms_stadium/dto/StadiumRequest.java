package com.utp.ms_stadium.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StadiumRequest {
    @NotBlank(message = "ownerEmail is required")
    @Email(message = "ownerEmail must be a valid email")
    private String ownerEmail;
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotNull
    @Positive
    private Integer capacity;

    @NotBlank
    private String fieldType;

    @NotNull
    @Positive
    private Double pricePerHour;
}