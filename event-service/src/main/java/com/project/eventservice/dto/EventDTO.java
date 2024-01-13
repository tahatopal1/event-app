package com.project.eventservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class EventDTO {

    private Long id;
    private String name;
    private String slogan;
    private String description;
    private Double price;
    private Long capacity;
    private Date eventDate;
    private String eventType;

}
