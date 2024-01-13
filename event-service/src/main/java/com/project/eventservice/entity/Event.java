package com.project.eventservice.entity;

import com.project.eventservice.enums.EventType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "_event")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String slogan;
    private Double price;

    @Lob
    @Column(length = 100000)
    private String description;

    private Long capacity;

    @Temporal(TemporalType.DATE)
    private Date eventDate;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

}
