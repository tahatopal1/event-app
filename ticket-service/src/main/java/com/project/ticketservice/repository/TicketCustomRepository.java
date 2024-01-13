package com.project.ticketservice.repository;

import com.project.ticketservice.dto.TicketFilterDTO;
import com.project.ticketservice.entity.Ticket;

import java.util.List;

public interface TicketCustomRepository {
    List<Ticket> findTicketsByCriteria(TicketFilterDTO ticketFilter);
}
