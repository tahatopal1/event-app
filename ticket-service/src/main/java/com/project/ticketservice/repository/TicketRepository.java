package com.project.ticketservice.repository;

import com.project.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Long> { }
