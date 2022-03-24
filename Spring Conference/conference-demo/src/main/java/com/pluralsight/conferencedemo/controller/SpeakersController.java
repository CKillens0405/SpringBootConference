package com.pluralsight.conferencedemo.controller;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    @PutMapping
    @RequestMapping("/update/{id}")
    public ResponseEntity<Speaker> update(@PathVariable(value = "id") Long id, @RequestBody Speaker speaker){
        Speaker currentSpeaker = speakerRepository.getById(id);

        currentSpeaker.setSpeaker_bio(speaker.getSpeaker_bio());
        currentSpeaker.setCompany(speaker.getCompany());
        currentSpeaker.setSpeaker_photo(speaker.getSpeaker_photo());
        currentSpeaker.setFirst_name(speaker.getFirst_name());
        currentSpeaker.setLast_name((speaker.getLast_name()));
        currentSpeaker.setSessions(speaker.getSessions());
        currentSpeaker.setTitle(speaker.getTitle());

        Speaker updatedSpeaker = speakerRepository.save(currentSpeaker);
        return ResponseEntity.ok(updatedSpeaker);

    }
}
