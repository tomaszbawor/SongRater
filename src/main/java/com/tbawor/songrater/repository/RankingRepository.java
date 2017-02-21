package com.tbawor.songrater.repository;

import com.tbawor.songrater.domain.Ranking;
import org.springframework.data.repository.CrudRepository;

public interface RankingRepository extends CrudRepository<Ranking, Long> {
}
