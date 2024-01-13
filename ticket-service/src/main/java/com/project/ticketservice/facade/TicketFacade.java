package com.project.ticketservice.facade;


import com.project.ticketservice.client.EventClient;
import com.project.ticketservice.dto.TicketDTO;
import com.project.ticketservice.dto.TicketFilterDTO;
import com.project.ticketservice.entity.Ticket;
import com.project.ticketservice.mapper.TicketMapper;
import com.project.ticketservice.repository.TicketCustomRepositoryImpl;
import com.project.ticketservice.service.TicketService;
import com.project.ticketservice.util.GeneralUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketFacade {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final TicketCustomRepositoryImpl ticketCustomRepository;
    private final EventClient eventClient;

    public List<TicketDTO> findAllTickets(TicketFilterDTO ticketFilter){
        return ticketCustomRepository.findTicketsByCriteria(ticketFilter)
                .stream()
                .map(ticketMapper::map)
                .toList();
    }

    public List<TicketDTO> listTickets(TicketFilterDTO ticketFilter) {
        ticketFilter.setUserId(GeneralUtil.getUserIdFromRequest());
        return this.findAllTickets(ticketFilter);
    }

    public TicketDTO findTicket(Long id){
        Ticket ticket = ticketService.findTicket(id);
        return ticketMapper.map(ticket);
    }

    public TicketDTO saveTicket(TicketDTO ticketDTO){
        Ticket ticket = ticketMapper.reverseMap(ticketDTO);
        eventClient.decreaseAmount(ticketDTO.getUserId(), ticketDTO.getCount());
        return saveAndMap(ticket);
    }

    public TicketDTO purchaseTicket(TicketDTO ticketDTO) {
        ticketDTO.setUserId(GeneralUtil.getUserIdFromRequest());
        return this.saveTicket(ticketDTO);
    }

    public void deleteTicket(Long id) {
        ticketService.deleteTicket(id);
    }

    public void cancelTicket(Long id) {
        Ticket ticket = ticketService.findTicket(id);
        if (!ticket.getUserId().equals(id)) {
            throw new RuntimeException("Not authorized to make the change");
        }
        ticket.setSuspended(true);
        ticketService.saveTicket(ticket);
    }

    private TicketDTO saveAndMap(Ticket ticket) {
        Ticket savedTicket = ticketService.saveTicket(ticket);
        return ticketMapper.map(savedTicket);
    }
    
}
