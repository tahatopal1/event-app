package com.project.ticketservice.mapper;

import com.project.ticketservice.dto.TicketDTO;
import com.project.ticketservice.entity.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper implements ObjectMapper<Ticket, TicketDTO> {

    @Override
    public TicketDTO map(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .code(ticket.getCode())
                .count(ticket.getCount())
                .totalPrice(ticket.getTotalPrice())
                .userId(ticket.getUserId())
                .eventId(ticket.getUserId())
                .isSuspended(ticket.isSuspended())
                .build();
    }

    @Override
    public Ticket reverseMap(TicketDTO ticketDTO) {
        return Ticket.builder()
                .code(ticketDTO.getCode())
                .count(ticketDTO.getCount())
                .totalPrice(ticketDTO.getTotalPrice())
                .userId(ticketDTO.getUserId())
                .eventId(ticketDTO.getUserId())
                .isSuspended(ticketDTO.isSuspended())
                .build();
    }
}
