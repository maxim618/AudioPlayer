package com.playmy;

import com.playmy.model.Track;
import com.playmy.util.TrackUtil;
import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrackUtilTest {

    @Test
    void handleMetadataUsesFallbacksForEmptyValues() {
        Track track = new Track();
        track.setFileName(new SimpleStringProperty("demo.mp3"));

        TrackUtil.handleMetadata("album", "", track);
        TrackUtil.handleMetadata("artist", "", track);
        TrackUtil.handleMetadata("title", "", track);
        TrackUtil.handleMetadata("year", "", track);

        assertEquals("Unknown", track.getAlbum().getValue());
        assertEquals("Unknown", track.getArtist().getValue());
        assertEquals("demo", track.getTitle().getValue());
        assertEquals("Unknown", track.getYear().getValue());
    }

    @Test
    void handleMetadataUsesProvidedValues() {
        Track track = new Track();
        track.setFileName(new SimpleStringProperty("demo.mp3"));

        TrackUtil.handleMetadata("album", "Discovery", track);
        TrackUtil.handleMetadata("album artist", "Daft Punk", track);
        TrackUtil.handleMetadata("title", "Harder", track);
        TrackUtil.handleMetadata("year", "2001", track);

        assertEquals("Discovery", track.getAlbum().getValue());
        assertEquals("Daft Punk", track.getArtist().getValue());
        assertEquals("Harder", track.getTitle().getValue());
        assertEquals("2001", track.getYear().getValue());
    }

    @Test
    void handleMetadataIgnoresUnrelatedKeys() {
        Track track = new Track();
        track.setFileName(new SimpleStringProperty("demo.mp3"));

        TrackUtil.handleMetadata("genre", "Electronic", track);

        assertNotNull(track.getFileName());
    }
}
