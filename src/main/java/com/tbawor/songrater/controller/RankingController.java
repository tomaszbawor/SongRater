package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rankings/")
public class RankingController {

    private final RankingRepository rankingRepository;

    @Autowired
    public RankingController(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Ranking saveRanking(@RequestBody Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ranking getById(@PathVariable(name = "id") Long id) {
        return rankingRepository.findOne(id);
    }
}
