package com.project.eventservice.mapper;

import com.project.eventservice.dto.EventDTO;
import com.project.eventservice.entity.Event;
import com.project.eventservice.enums.EventType;
import org.springframework.stereotype.Component;

@Component
public class EventMapper implements ObjectMapper<Event, EventDTO> {
    @Override
    public EventDTO map(Event event) {
        return EventDTO.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .slogan(event.getSlogan())
                .price(event.getPrice())
                .capacity(event.getCapacity())
                .eventDate(event.getEventDate())
                .eventType(event.getEventType().name())
                .build();
    }

    @Override
    public Event reverseMap(EventDTO eventDTO) {
        return Event.builder()
                .name(eventDTO.getName())
                .description(eventDTO.getDescription())
                .slogan(eventDTO.getSlogan())
                .price(eventDTO.getPrice())
                .capacity(eventDTO.getCapacity())
                .eventDate(eventDTO.getEventDate())
                .eventType(EventType.valueOf(eventDTO.getEventType()))
                .build();
    }
}
