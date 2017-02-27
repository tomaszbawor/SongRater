package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Song;
import com.tbawor.songrater.repository.SongRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SongControllerTest {

    @Mock
    private SongRepository repository;

    @InjectMocks
    private SongController controller;

    @Test
    public void shouldSaveSongByRepository() {
        // given
        Song song = new Song();

        // when
        controller.saveSong(song);

        // then
        verify(repository, times(1)).save(eq(song));
    }

    @Test
    public void shouldFindSongByIdInRepository() {
        // given
        Long id = 2324L;
        Song stubSongInDatabase = new Song();
        when(repository.findOne(eq(id))).thenReturn(stubSongInDatabase);

        // when
        Song foundSong = controller.getSongById(id);

        // then
        verify(repository, times(1)).findOne(eq(id));
        assertThat(foundSong).isEqualTo(stubSongInDatabase);
    }

}