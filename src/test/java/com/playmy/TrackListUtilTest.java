package com.playmy;

import com.playmy.model.TrackList;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.playmy.util.TrackListUtil;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackListUtilTest {

    private final Preferences prefs = Preferences.userRoot().node("trackio.tracklists");

    @BeforeEach
    void clearPreferences() throws BackingStoreException {
        prefs.clear();
        prefs.putInt("TRACKLIST_NUMBER", 0);
    }

    @AfterEach
    void cleanup() throws BackingStoreException {
        prefs.clear();
        prefs.putInt("TRACKLIST_NUMBER", 0);
    }

    @Test
    void saveTrackListPersistsAndLoads() {
        TrackList trackList = new TrackList(0, "Favorites", "/music/favorites");

        TrackListUtil.saveTrackList(trackList);
        ObservableList<TrackList> result = TrackListUtil.getAll();

        assertEquals(1, TrackListUtil.getTrackListsNumber());
        assertEquals(1, result.size());
        assertEquals("Favorites", result.get(0).getName());
        assertEquals("/music/favorites", result.get(0).getPath());
    }


    @Test
    void saveTrackListUpdatesExistingTrackListById() {
        TrackListUtil.saveTrackList(new TrackList(0, "Morning", "/music/morning"));

        TrackList updated = new TrackList(1, "Morning Updated", "/music/morning-new");
        TrackListUtil.saveTrackList(updated);

        ObservableList<TrackList> result = TrackListUtil.getAll();

        assertEquals(1, TrackListUtil.getTrackListsNumber());
        assertEquals(1, result.size());
        assertEquals("Morning Updated", result.get(0).getName().getValue());
        assertEquals("/music/morning-new", result.get(0).getPath().getValue());
    }

    @Test
    void deleteRemovesAndCompactsTrackLists() {
        TrackList first = new TrackList(0, "Morning", "/music/morning");
        TrackList second = new TrackList(0, "Evening", "/music/evening");

        TrackListUtil.saveTrackList(first);
        TrackListUtil.saveTrackList(second);

        ObservableList<TrackList> initial = TrackListUtil.getAll();
        TrackListUtil.delete(initial.get(0));

        ObservableList<TrackList> result = TrackListUtil.getAll();

        assertEquals(1, TrackListUtil.getTrackListsNumber());
        assertEquals(1, result.size());
        assertEquals("Evening", result.get(0).getName());
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void deleteAllClearsPreferences() {
        TrackListUtil.saveTrackList(new TrackList(0, "One", "/music/one"));
        TrackListUtil.saveTrackList(new TrackList(0, "Two", "/music/two"));

        TrackListUtil.deleteAll();

        assertEquals(0, TrackListUtil.getTrackListsNumber());
        assertTrue(TrackListUtil.getAll().isEmpty());
    }
}