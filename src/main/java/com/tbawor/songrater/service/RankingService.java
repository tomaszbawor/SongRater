package com.tbawor.songrater.service;

import com.tbawor.songrater.domain.Ranking;
import com.tbawor.songrater.domain.Song;
import com.tbawor.songrater.domain.SongRanking;
import com.tbawor.songrater.repository.RankingRepository;
import com.tbawor.songrater.repository.SongRankingRepository;
import com.tbawor.songrater.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingService {

    private final RankingRepository rankingRepository;
    private final SongRepository songRepository;
    private final SongRankingRepository songRankingRepository;

    @Autowired
    public RankingService(RankingRepository rankingRepository,SongRepository songRepository, SongRankingRepository songRankingRepository) {
        this.rankingRepository = rankingRepository;
        this.songRepository = songRepository;
        this.songRankingRepository = songRankingRepository;
    }

    public Ranking saveRanking(Ranking ranking) {
        return rankingRepository.save(ranking);
    }


    public Ranking findRankingById(Long id) {
        return rankingRepository.findOne(id);
    }

    public SongRanking addSongToRanking(Long rankingId, Song song) {
        Ranking ranking = rankingRepository.findOne(rankingId);
        Boolean shouldRankingBeActive = song.canBeAddedToRanking();
        SongRanking songRanking = new SongRanking(new SongRanking.SongRankingKey(ranking, song), 0L, shouldRankingBeActive);

        songRankingRepository.save(songRanking);
        return songRanking;
    }

    public SongRanking upvoteSongInRanking(Long rankingId, Long songId) {
        SongRanking songRanking = getSongRanking(rankingId, songId);
        songRanking.setValue(songRanking.getValue() + 1);
        return songRankingRepository.save(songRanking);
    }

    public SongRanking downvoteSongInRanking(Long rankingId, Long songId) {
        SongRanking songRanking = getSongRanking(rankingId, songId);
        songRanking.setValue(songRanking.getValue() - 1);
        return songRankingRepository.save(songRanking);
    }

    public List<Song> getTopFiveForRankingWithId(Long rankingId) {
        return songRankingRepository
                .findBySongRankingKeyRankingId(rankingId)
                .stream()
                .filter(SongRanking::getActive)
                .sorted(Comparator.comparing(SongRanking::getValue))
                .limit(5)
                .map(SongRanking::getSongRankingKey)
                .map(SongRanking.SongRankingKey::getSong)
                .collect(Collectors.toList());
    }

    private SongRanking getSongRanking(Long rankingId, Long songId) {
        Ranking ranking = rankingRepository.findOne(rankingId);
        Song song = songRepository.findOne(songId);

        return songRankingRepository.findOne(new SongRanking.SongRankingKey(ranking, song));
    }
}
