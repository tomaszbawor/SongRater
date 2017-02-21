package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/rankings/")
public class RankingController {

    @Autowired
    private RankingRepository rankingRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Ranking saveRanking(@RequestBody Ranking ranking) {
        rankingRepository.save(ranking);
        return ranking;
    }
}
