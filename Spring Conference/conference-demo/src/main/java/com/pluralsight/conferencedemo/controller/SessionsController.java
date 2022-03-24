package com.pluralsight.conferencedemo.controller;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @PutMapping
    @RequestMapping("/update/{id}")
    public ResponseEntity<Session> update(@PathVariable(value = "id") Long id, @RequestBody Session session){

        Session currentSession = sessionRepository.getById(id);
        currentSession.setSession_description(session.getSession_description());
        currentSession.setSession_name(session.getSession_name());
        currentSession.setSession_length(session.getSession_length());
        currentSession.setSpeakers(session.getSpeakers());

        final Session updatedSession = sessionRepository.save(currentSession);
        return ResponseEntity.ok(updatedSession);


    }
}
