package com.project.ticketservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketFilterDTO {

    private Long minCount;
    private Long maxCount;
    private Double minTotalPrice;
    private Double maxTotalPrice;
    private Long eventId;
    private Long userId;
    private Integer page = 1;
    private Integer size = 10;

}
