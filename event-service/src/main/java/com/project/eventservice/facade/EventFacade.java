package com.project.eventservice.facade;

import com.project.eventservice.dto.EventDTO;
import com.project.eventservice.entity.Event;
import com.project.eventservice.mapper.EventMapper;
import com.project.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventFacade {
    
    private final EventService eventService;
    private final EventMapper eventMapper;
    
    public List<EventDTO> findAllEvents(Pageable pageable) {
        return eventService.findAllEvents(pageable)
                .getContent()
                .stream()
                .map(eventMapper::map)
                .toList();
    }

    public EventDTO findEvent(Long id){
        Event event = eventService.findEvent(id);
        return eventMapper.map(event);
    }

    public EventDTO saveEvent(EventDTO eventDTO){
        Event event = eventMapper.reverseMap(eventDTO);
        return saveAndMap(event);
    }

    public EventDTO updateEvent(Long id, EventDTO eventDTO){
        Event savingEvent = eventMapper.reverseMap(eventDTO);
        savingEvent.setId(id);
        return saveAndMap(savingEvent);
    }

    public void deleteEvent(Long id){
        eventService.deleteEvent(id);
    }

    public void decreasePurchased(Long id, Long count) {
        Event event = eventService.findEvent(id);
        long leftOver = event.getCapacity() - count;
        if (leftOver <= 0) {
            throw new RuntimeException("Not enough capacity!");
        }
        event.setCapacity(leftOver);
        eventService.saveEvent(event);
    }

    private EventDTO saveAndMap(Event event) {
        Event savedEvent = eventService.saveEvent(event);
        return eventMapper.map(savedEvent);
    }
    
}
