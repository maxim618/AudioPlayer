package com.playmy;

import com.playmy.model.Track;
import com.playmy.util.TrackUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrackUtilTest {

    @Test
    void handleMetadataUsesFallbacksForEmptyValues() {
        Track track = new Track();
        track.setFileName("demo.mp3");

        TrackUtil.handleMetadata("album", "", track);
        TrackUtil.handleMetadata("artist", "", track);
        TrackUtil.handleMetadata("title", "", track);
        TrackUtil.handleMetadata("year", "", track);

        assertEquals("Unknown", track.getAlbum());
        assertEquals("Unknown", track.getArtist());
        assertEquals("demo", track.getTitle());
        assertEquals("Unknown", track.getYear());
    }

    @Test
    void handleMetadataUsesProvidedValues() {
        Track track = new Track();
        track.setFileName("demo.mp3");

        TrackUtil.handleMetadata("album", "Discovery", track);
        TrackUtil.handleMetadata("album artist", "Daft Punk", track);
        TrackUtil.handleMetadata("title", "Harder", track);
        TrackUtil.handleMetadata("year", "2001", track);

        assertEquals("Discovery", track.getAlbum());
        assertEquals("Daft Punk", track.getArtist());
        assertEquals("Harder", track.getTitle());
        assertEquals("2001", track.getYear());
    }

    @Test
    void handleMetadataIgnoresUnrelatedKeys() {
        Track track = new Track();
        track.setFileName("demo.mp3");

        TrackUtil.handleMetadata("genre", "Electronic", track);

        assertNotNull(track.getFileName());
    }
}
