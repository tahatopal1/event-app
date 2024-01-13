package com.project.ticketservice.service;

import com.project.ticketservice.entity.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAllTickets();

    Ticket findTicket(Long id);

    Ticket saveTicket(Ticket ticket);

    void deleteTicket(Long id);

}
