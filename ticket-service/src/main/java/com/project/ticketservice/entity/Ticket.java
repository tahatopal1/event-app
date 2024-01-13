package com.project.ticketservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Integer count;
    private Double totalPrice;
    private Long userId;
    private Long eventId;
    private boolean isSuspended;

}
