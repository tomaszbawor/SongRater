package com.tbawor.songrater.repository;

import com.tbawor.songrater.domain.SongRanking;
import org.springframework.data.repository.CrudRepository;

public interface SongRankingRepository extends CrudRepository<SongRanking, SongRanking.SongRankingKey> {
}
