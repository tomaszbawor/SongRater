package com.tbawor.songrater.controller;

import com.tbawor.songrater.domain.Song;
import com.tbawor.songrater.repository.SongRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(SongController.class)
public class SongControllerTestIT {

    private static final String SONGS_URL = "/api/songs/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SongRepository songRepository;

    @Test
    public void shouldProperlyParseParameterFromUrlWhenGettingById() throws Exception {
        // given
        long searchedSongId = 543L;
        doReturn(mockSong()).when(songRepository).findOne(eq(searchedSongId));

        // when
        mockMvc.perform(get(SONGS_URL + searchedSongId)
                .accept(MediaType.APPLICATION_JSON_UTF8))

        // then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{'id': 543, 'title':'FAKE TITLE', 'url': 'http://FakeURL.com'}".replace('\'', '"')));

        verify(songRepository, times(1)).findOne(eq(543L));
    }

    private Song mockSong() {
        Song song = new Song();
        song.setId(543L);
        song.setTitle("FAKE TITLE");
        song.setUrl("http://FakeURL.com");
        return song;
    }
}