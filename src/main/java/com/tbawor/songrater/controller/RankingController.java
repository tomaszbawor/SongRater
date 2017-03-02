package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.domain.Song;
import com.tbawor.songrater.domain.SongRanking;
import com.tbawor.songrater.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rankings/")
public class RankingController {

    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Ranking saveRanking(@RequestBody Ranking ranking) {
        return rankingService.saveRanking(ranking);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ranking getById(@PathVariable(name = "id") Long id) {
        return rankingService.findRankingById(id);
    }

    @RequestMapping(value = "/{id}/songs", method = RequestMethod.POST)
    public SongRanking addSongToRanking(@PathVariable(name = "id") Long id, @RequestBody Song song) {
        return rankingService.addSongToRanking(id, song);
    }

    @RequestMapping(value = "/{id}/songs/{songId}/upvote", method = RequestMethod.POST)
    public SongRanking upVoteRankingSong(@PathVariable(name = "id") Long id, @PathVariable(name = "songId") Long songId) {
        return rankingService.upvoteSongInRanking(id, songId);
    }

    @RequestMapping(value = "/{id}/songs/{songId}/downvote", method = RequestMethod.POST)
    public SongRanking downVoteRankingSong(@PathVariable(name = "id") Long id, @PathVariable(name = "songId") Long songId) {
        return rankingService.downvoteSongInRanking(id, songId);
    }

    @RequestMapping(value = "/{id}/top5", method = RequestMethod.GET)
    public List<Song> getTopFive(@PathVariable(name = "id") Long id) {
        return rankingService.getTopFiveForRankingWithId(id);
    }
}
