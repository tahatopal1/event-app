package com.project.eventservice.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class EventDTO {

    private Long id;  // Assuming ID is auto-generated or not validated

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @Size(max = 150, message = "Slogan should not exceed 150 characters")
    private String slogan;

    @Size(max = 1000, message = "Description should not exceed 1000 characters")
    private String description;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Capacity is mandatory")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Long capacity;

    @NotNull(message = "Event date is mandatory")
    @Future(message = "Event date must be in the future")
    private Date eventDate;

    @NotBlank(message = "Event type is mandatory")
    private String eventType;

}
