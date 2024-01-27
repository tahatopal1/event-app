package com.project.ticketservice.service;

import com.project.ticketservice.entity.Ticket;
import com.project.ticketservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findTicket(Long id) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(id);
        if (ticketOpt.isEmpty()) {
            throw new RuntimeException("There's no ticket with id: " + id);
        }
        return ticketOpt.get();
    }

    @Override
    public Ticket getTicketForUser(Long id, Long userId) {
        Optional<Ticket> ticketOpt = ticketRepository.findByIdAndUserId(id, userId);
        if (ticketOpt.isEmpty()) {
            throw new RuntimeException(String.format("There's no ticket with id: %s and userId: %s", id, userId));
        }
        return ticketOpt.get();
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
