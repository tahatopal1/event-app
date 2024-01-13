package com.project.ticketservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TicketDTO {

    private Long id;
    private String code;
    private Integer count;
    private Double totalPrice;
    private Long userId;
    private Long eventId;
    private boolean isSuspended;

}
