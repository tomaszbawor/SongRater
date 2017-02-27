package com.tbawor.songrater.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class SongSerializationTest {

    @Autowired
    private JacksonTester<Song> json;

    @Test
    public void shouldPropertySerializeSongToJson() throws IOException {
        // given
        Song song = new Song();
        song.setTitle("Bożenka");
        song.setId(12L);
        song.setUrl("http://fake.url/some.mp3");

        // when
        JsonContent<Song> serializedContent = json.write(song);

        // then
        assertThat(serializedContent).extractingJsonPathNumberValue("@.id").isEqualTo(12);
        assertThat(serializedContent).extractingJsonPathStringValue("@.title").isEqualTo("Bożenka");
        assertThat(serializedContent).extractingJsonPathStringValue("@.url").isEqualTo("http://fake.url/some.mp3");
    }

    @Test
    public void shouldProperlyDeserializeSongFromJson() throws IOException {
        // given
        String content = "{'id': 123, 'title':'Test', 'url':'http://fake.test'}".replace('\'', '"');

        // when
        ObjectContent<Song> parsedSong = json.parse(content);

        // then
        assertThat(parsedSong.getObject().getId()).isEqualTo(123);
        assertThat(parsedSong.getObject().getTitle()).isEqualTo("Test");
        assertThat(parsedSong.getObject().getUrl()).isEqualTo("http://fake.test");
    }
}