package com.project.ticketservice.facade;

import com.project.ticketservice.client.EventClient;
import com.project.ticketservice.dto.TicketDTO;
import com.project.ticketservice.dto.TicketFilterDTO;
import com.project.ticketservice.entity.Ticket;
import com.project.ticketservice.facade.TicketFacade;
import com.project.ticketservice.mapper.TicketMapper;
import com.project.ticketservice.repository.TicketCustomRepositoryImpl;
import com.project.ticketservice.service.TicketService;
import com.project.ticketservice.util.GeneralUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketFacadeTest {

    @Mock
    private TicketService ticketService;
    @Mock
    private TicketMapper ticketMapper;
    @Mock
    private TicketCustomRepositoryImpl ticketCustomRepository;
    @Mock
    private EventClient eventClient;

    @InjectMocks
    private TicketFacade ticketFacade;

    private Ticket ticket;
    private TicketDTO ticketDTO;
    private TicketFilterDTO ticketFilterDTO;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
        ticket.setId(1L);
        // ... other properties

        ticketDTO = TicketDTO.builder().build();
        ticketDTO.setId(1L);
        // ... other properties

        ticketFilterDTO = new TicketFilterDTO();
        // ... set properties if needed
    }

    @Test
    void findAllTickets_ShouldReturnAllTickets() {
        when(ticketCustomRepository.findTicketsByCriteria(any(TicketFilterDTO.class))).thenReturn(Arrays.asList(ticket));
        when(ticketMapper.map(any(Ticket.class))).thenReturn(ticketDTO);

        List<TicketDTO> result = ticketFacade.findAllTickets(ticketFilterDTO);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(ticketCustomRepository, times(1)).findTicketsByCriteria(any(TicketFilterDTO.class));
        verify(ticketMapper, times(1)).map(any(Ticket.class));
    }

    @Test
    void listTickets_ShouldReturnTicketsForUser() {
        try (MockedStatic<GeneralUtil> mocked = Mockito.mockStatic(GeneralUtil.class)) {
            mocked.when(GeneralUtil::getUserIdFromRequest).thenReturn(1L);

            when(ticketCustomRepository.findTicketsByCriteria(ticketFilterDTO)).thenReturn(Arrays.asList(ticket));
            when(ticketMapper.map(any(Ticket.class))).thenReturn(ticketDTO);

            List<TicketDTO> result = ticketFacade.listTickets(ticketFilterDTO);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(ticketCustomRepository, times(1)).findTicketsByCriteria(ticketFilterDTO);
            verify(ticketMapper, times(1)).map(any(Ticket.class));
        }
    }

    @Test
    void findTicket_ShouldReturnTicket() {
        when(ticketService.findTicket(anyLong())).thenReturn(ticket);
        when(ticketMapper.map(any(Ticket.class))).thenReturn(ticketDTO);

        TicketDTO result = ticketFacade.findTicket(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ticketService, times(1)).findTicket(anyLong());
        verify(ticketMapper, times(1)).map(any(Ticket.class));
    }

    @Test
    void findTicketForUser_ShouldReturnTicketForUser() {
        try (MockedStatic<GeneralUtil> mocked = Mockito.mockStatic(GeneralUtil.class)) {
            mocked.when(GeneralUtil::getUserIdFromRequest).thenReturn(1L);

            when(ticketService.getTicketForUser(anyLong(), anyLong())).thenReturn(ticket);
            when(ticketMapper.map(any(Ticket.class))).thenReturn(ticketDTO);

            TicketDTO result = ticketFacade.findTicketForUser(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId());
            verify(ticketService, times(1)).getTicketForUser(anyLong(), anyLong());
            verify(ticketMapper, times(1)).map(any(Ticket.class));
        }
    }

    @Test
    void saveTicket_ShouldSaveAndReturnTicket() {
        // Set expected values in ticketDTO
        ticketDTO.setEventId(1L); // Assuming 1L as an example eventId
        ticketDTO.setCount(1); // Assuming 1 as an example count

        when(ticketMapper.reverseMap(any(TicketDTO.class))).thenReturn(ticket);
        when(ticketService.saveTicket(any(Ticket.class))).thenReturn(ticket);
        when(ticketMapper.map(any(Ticket.class))).thenReturn(ticketDTO);

        // Stub eventClient.decreaseAmount with argument matchers
        doNothing().when(eventClient).decreaseAmount(anyLong(), anyInt());

        TicketDTO result = ticketFacade.saveTicket(ticketDTO);

        assertNotNull(result);
        verify(ticketMapper, times(1)).reverseMap(any(TicketDTO.class));
        verify(eventClient, times(1)).decreaseAmount(anyLong(), anyInt()); // Verify with argument matchers
        verify(ticketService, times(1)).saveTicket(any(Ticket.class));
        verify(ticketMapper, times(1)).map(any(Ticket.class));
    }


    @Test
    void purchaseTicket_ShouldPurchaseTicket() {
        try (MockedStatic<GeneralUtil> mocked = Mockito.mockStatic(GeneralUtil.class)) {
            mocked.when(GeneralUtil::getUserIdFromRequest).thenReturn(1L);

            // Set expected values in ticketDTO
            ticketDTO.setEventId(1L); // Assuming 1L as an example eventId
            ticketDTO.setCount(1); // Assuming 1 as an example count

            when(ticketMapper.reverseMap(any(TicketDTO.class))).thenReturn(ticket);
            when(ticketService.saveTicket(any(Ticket.class))).thenReturn(ticket);
            when(ticketMapper.map(any(Ticket.class))).thenReturn(ticketDTO);

            // Stub eventClient.decreaseAmount with argument matchers or specific values
            doNothing().when(eventClient).decreaseAmount(anyLong(), anyInt());

            TicketDTO result = ticketFacade.purchaseTicket(ticketDTO);

            assertNotNull(result);
            assertEquals(1L, result.getUserId()); // Ensure that userId is set correctly
            verify(ticketMapper, times(1)).reverseMap(any(TicketDTO.class));
            verify(eventClient, times(1)).decreaseAmount(anyLong(), anyInt()); // Verify with argument matchers
            verify(ticketService, times(1)).saveTicket(any(Ticket.class));
            verify(ticketMapper, times(1)).map(any(Ticket.class));
        }
    }



    @Test
    void deleteTicket_ShouldInvokeServiceDelete() {
        doNothing().when(ticketService).deleteTicket(anyLong());

        ticketFacade.deleteTicket(1L);

        verify(ticketService, times(1)).deleteTicket(anyLong());
    }

    @Test
    void cancelTicket_ShouldCancelTicket() {
        try (MockedStatic<GeneralUtil> mocked = Mockito.mockStatic(GeneralUtil.class)) {
            mocked.when(GeneralUtil::getUserIdFromRequest).thenReturn(1L);

            when(ticketService.getTicketForUser(anyLong(), anyLong())).thenReturn(ticket);
            when(ticketService.saveTicket(any(Ticket.class))).thenReturn(ticket);

            ticketFacade.cancelTicket(1L);

            assertTrue(ticket.isSuspended());
            verify(ticketService, times(1)).getTicketForUser(anyLong(), anyLong());
            verify(ticketService, times(1)).saveTicket(ticket);
        }
    }

}