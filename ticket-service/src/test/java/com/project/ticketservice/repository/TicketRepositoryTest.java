package com.project.ticketservice.repository;

// ... (import statements)

import com.project.ticketservice.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TicketRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    private Ticket savedTicket;

    @BeforeEach
    void setUp() {
        // Setup data for each test
        Ticket ticket = new Ticket();
        // ... set properties of ticket
        savedTicket = entityManager.persistAndFlush(ticket);
    }

    @Test
    void findByIdAndUserId_WhenTicketExists_ShouldReturnTicket() {
        Optional<Ticket> foundTicket = ticketRepository.findByIdAndUserId(savedTicket.getId(), savedTicket.getUserId());

        assertTrue(foundTicket.isPresent(), "Ticket should be found");
        assertEquals(savedTicket.getId(), foundTicket.get().getId(), "Found ticket should have the correct ID");
        assertEquals(savedTicket.getUserId(), foundTicket.get().getUserId(), "Found ticket should have the correct UserID");
    }

    // You can add more tests for different scenarios
}
