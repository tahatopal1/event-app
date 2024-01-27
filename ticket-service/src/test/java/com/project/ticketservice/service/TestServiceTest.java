package com.project.ticketservice.service;

import com.project.ticketservice.entity.Ticket;
import com.project.ticketservice.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
        ticket.setId(1L);
        // set other properties
    }

    @Test
    void findAllTickets_ShouldReturnAllTickets() {
        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket));

        List<Ticket> result = ticketService.findAllTickets();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void findTicket_WhenTicketExists_ShouldReturnTicket() {
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(ticket));

        Ticket result = ticketService.findTicket(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ticketRepository, times(1)).findById(anyLong());
    }

    @Test
    void findTicket_WhenTicketDoesNotExist_ShouldThrowException() {
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> ticketService.findTicket(1L));

        assertEquals("There's no ticket with id: 1", exception.getMessage());
        verify(ticketRepository, times(1)).findById(anyLong());
    }

    @Test
    void getTicketForUser_WhenTicketExists_ShouldReturnTicket() {
        when(ticketRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(ticket));

        Ticket result = ticketService.getTicketForUser(1L, 1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ticketRepository, times(1)).findByIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void getTicketForUser_WhenTicketDoesNotExist_ShouldThrowException() {
        when(ticketRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> ticketService.getTicketForUser(1L, 1L));

        assertEquals("There's no ticket with id: 1 and userId: 1", exception.getMessage());
        verify(ticketRepository, times(1)).findByIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void saveTicket_ShouldReturnSavedTicket() {
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket result = ticketService.saveTicket(ticket);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    void deleteTicket_ShouldInvokeRepositoryDelete() {
        doNothing().when(ticketRepository).deleteById(anyLong());

        ticketService.deleteTicket(1L);

        verify(ticketRepository, times(1)).deleteById(anyLong());
    }

}
