package com.project.eventservice.controller;

import com.project.eventservice.dto.EventDTO;
import com.project.eventservice.facade.EventFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    
    private final EventFacade eventFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_STRONG, ROLE_WEAK')")
    public List<EventDTO> getAllEvents(){
        return eventFacade.findAllEvents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_STRONG, ROLE_WEAK')")
    public EventDTO getEvent(@PathVariable("id") Long id){
        return eventFacade.findEvent(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_STRONG')")
    public EventDTO saveEvent(@RequestBody EventDTO eventDTO){
        return eventFacade.saveEvent(eventDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ROLE_STRONG')")
    public EventDTO updateEvent(@PathVariable("id") Long id, @RequestBody EventDTO eventDTO){
        return eventFacade.updateEvent(id, eventDTO);
    }

    @PutMapping("/decrease/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_AUTOMATION')")
    public void decreasePurchasedAmount(@PathVariable("id") Long id, @RequestParam Long count) {
        eventFacade.decreasePurchased(id, count);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_STRONG')")
    public void deleteEvent(@PathVariable("id") Long id){
        eventFacade.deleteEvent(id);
    }
    
}
