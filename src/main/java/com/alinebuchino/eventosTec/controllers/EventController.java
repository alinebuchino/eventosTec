package com.alinebuchino.eventosTec.controllers;

import com.alinebuchino.eventosTec.DTOs.EventRequestDTO;
import com.alinebuchino.eventosTec.domain.Event;
import com.alinebuchino.eventosTec.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create (@RequestParam("title") String title,
                                         @RequestParam(value = "description", required = false) String description,
                                         @RequestParam("date") Long date,
                                         @RequestParam("city") String city,
                                         @RequestParam("state") String state,
                                         @RequestParam("remote") Boolean remote,
                                         @RequestParam("eventUrl") String eventUrl,
                                         @RequestParam(value = "image", required = false) MultipartFile image
                                         ){
        var eventRequestDTO = new EventRequestDTO(title, description, date, city, state, remote, eventUrl, image);
        Event newEvent = this.eventService.createEvent(eventRequestDTO);
        return ResponseEntity.ok(newEvent);
    }
}
