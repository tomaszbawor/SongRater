package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Song;
import com.tbawor.songrater.repository.SongRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private SongRepository repository;

    public SongController(SongRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Song saveSong(Song song) {
        return repository.save(song);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Song getSongById(@PathVariable(name = "id") Long id) {
        return repository.findOne(id);
    }
}
