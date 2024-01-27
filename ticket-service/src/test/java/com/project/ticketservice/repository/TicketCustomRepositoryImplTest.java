package com.project.ticketservice.repository;

import com.project.ticketservice.dto.TicketFilterDTO;
import com.project.ticketservice.entity.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TicketCustomRepositoryImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Ticket> typedQuery;

    @InjectMocks
    private TicketCustomRepositoryImpl ticketCustomRepository;

    @Test
    void findTicketsByCriteria_ShouldReturnTickets() {
        // Arrange
        TicketFilterDTO ticketFilterDTO = new TicketFilterDTO();
        ticketFilterDTO.setMinCount(10l);
        ticketFilterDTO.setMaxCount(50l);
        ticketFilterDTO.setMinTotalPrice(100.00);
        ticketFilterDTO.setMaxTotalPrice(500.00);
        ticketFilterDTO.setEventId(1L);
        ticketFilterDTO.setUserId(2L);
        ticketFilterDTO.setPage(1);
        ticketFilterDTO.setSize(10);

        List<Ticket> expectedTickets = Arrays.asList(new Ticket(), new Ticket());

        when(entityManager.createQuery(anyString(), eq(Ticket.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedTickets);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.setFirstResult(anyInt())).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(anyInt())).thenReturn(typedQuery);

        // Act
        List<Ticket> tickets = ticketCustomRepository.findTicketsByCriteria(ticketFilterDTO);

        // Assert
        assertNotNull(tickets);
        assertEquals(expectedTickets.size(), tickets.size());
        verify(entityManager, times(1)).createQuery(anyString(), eq(Ticket.class));
        verify(typedQuery, times(1)).getResultList();

        verify(typedQuery).setParameter("minCount", ticketFilterDTO.getMinCount());
        verify(typedQuery).setParameter("maxCount", ticketFilterDTO.getMaxCount());
        verify(typedQuery).setParameter("minTotalPrice", ticketFilterDTO.getMinTotalPrice());
        verify(typedQuery).setParameter("maxTotalPrice", ticketFilterDTO.getMaxTotalPrice());
        verify(typedQuery).setParameter("eventId", ticketFilterDTO.getEventId());
        verify(typedQuery).setParameter("userId", ticketFilterDTO.getUserId());
        verify(typedQuery).setFirstResult((ticketFilterDTO.getPage() - 1) * ticketFilterDTO.getSize());
        verify(typedQuery).setMaxResults(ticketFilterDTO.getSize());
    }
}
