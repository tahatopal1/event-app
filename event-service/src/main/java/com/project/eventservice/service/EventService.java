package com.project.eventservice.service;

import com.project.eventservice.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    Page<Event> findAllEvents(Pageable pageable);

    Event findEvent(Long id);

    Event saveEvent(Event role);

    void deleteEvent(Long id);

}
