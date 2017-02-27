package com.tbawor.songrater.repository;

import com.tbawor.songrater.domain.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {
}
