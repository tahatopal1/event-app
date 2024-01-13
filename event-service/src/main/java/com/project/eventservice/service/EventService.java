package com.project.eventservice.service;

import com.project.eventservice.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> findAllEvents();

    Event findEvent(Long id);

    Event saveEvent(Event role);

    void deleteEvent(Long id);

}
