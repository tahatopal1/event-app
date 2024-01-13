package com.project.ticketservice.controller.admin;

import com.project.ticketservice.dto.TicketDTO;
import com.project.ticketservice.dto.TicketFilterDTO;
import com.project.ticketservice.facade.TicketFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/ticket")
public class TicketAdminController {

    private final TicketFacade ticketFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDTO> getAllTickets(@ModelAttribute TicketFilterDTO ticketFilter){
        return ticketFacade.findAllTickets(ticketFilter);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TicketDTO getTicket(@PathVariable("id") Long id){
        return ticketFacade.findTicket(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDTO saveTicket(@RequestBody TicketDTO ticketDTO){
        return ticketFacade.saveTicket(ticketDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable("id") Long id){
        ticketFacade.deleteTicket(id);
    }
    
}
