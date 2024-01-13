package com.project.ticketservice.controller;

import com.project.ticketservice.dto.TicketDTO;
import com.project.ticketservice.dto.TicketFilterDTO;
import com.project.ticketservice.facade.TicketFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketController {

    private final TicketFacade ticketFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDTO> getAllTickets(@ModelAttribute TicketFilterDTO ticketFilter){
        return ticketFacade.listTickets(ticketFilter);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketDTO getTicket(@PathVariable("id") Long id){
        return ticketFacade.findTicket(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDTO saveTicket(@RequestBody TicketDTO ticketDTO){
        return ticketFacade.purchaseTicket(ticketDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable("id") Long id){
        ticketFacade.cancelTicket(id);
    }
    
}
