package com.project.eventservice.service;

import com.project.eventservice.entity.Event;
import com.project.eventservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Page<Event> findAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public Event findEvent(Long id) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (eventOpt.isEmpty()) {
            throw new RuntimeException("There's no event with id: " + id);
        }
        return eventOpt.get();
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
