package com.playmy;

import com.playmy.model.Track;
import com.playmy.model.TrackList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.playmy.util.TrackUtil;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackUtilGetAllTest {

    @Test
    void getAllFiltersSupportedFilesAndBuildsTracks() throws IOException {
        Path tempDir = Files.createTempDirectory("trackutil");
        Files.createFile(tempDir.resolve("song.mp3"));
        Files.createFile(tempDir.resolve("notes.txt"));
        Files.createFile(tempDir.resolve("ambient.wav"));

        TrackList trackList = new TrackList(1, "Temp", tempDir.toString());
        ObservableList<Track> tracks = TrackUtil.getAll(trackList, null, mediaUrl -> null);

        assertEquals(1, tracks.size());
        Track track = tracks.get(0);
        assertEquals("song.mp3", track.getFileName());
        assertEquals(tempDir + "\\song.mp3", track.getPath());
        assertTrue(track.getMedia() == null);
    }
}