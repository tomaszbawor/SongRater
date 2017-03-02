package com.tbawor.songrater.repository;

import com.tbawor.songrater.domain.SongRanking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRankingRepository extends CrudRepository<SongRanking, SongRanking.SongRankingKey> {
    List<SongRanking> findBySongRankingKeyRankingId(Long rankingId);
}
