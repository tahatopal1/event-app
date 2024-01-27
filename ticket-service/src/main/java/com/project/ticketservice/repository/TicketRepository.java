package com.project.ticketservice.repository;

import com.project.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByIdAndUserId(Long id, Long userId);

}
