package com.playmy.util;

import java.util.prefs.Preferences;

import com.playmy.model.TrackList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * Utilities to deal with Tracklists.
 *
 */
public class TrackListUtil {

    private static final String TRACKLIST_NODE = "trackio.tracklists";
    private static final String TRACKLIST_NUMBER = "TRACKLIST_NUMBER";
    private static final String TRACKLIST_NAME = "TRACKLIST_";
    private static final String TRACKLIST_PATH = "TRACKLIST_PATH_";

    private static final Preferences prefs = Preferences.userRoot().node(TRACKLIST_NODE);

    /**
     * Save tracklist in preferences.
     */
    public static void saveTrackList(TrackList trackList) {
        int currentTrackListNumber = getTrackListsNumber();
        int id = trackList.getId();

        if (id > 0 && id <= currentTrackListNumber) {
            prefs.put(TRACKLIST_NAME + id, trackList.getName());
            prefs.put(TRACKLIST_PATH + id, trackList.getPath());
            return;
        }

        int nextTrackListNumber = currentTrackListNumber + 1;
        prefs.put(TRACKLIST_NAME + nextTrackListNumber, trackList.getName());
        prefs.put(TRACKLIST_PATH + nextTrackListNumber, trackList.getPath());
        prefs.putInt(TRACKLIST_NUMBER, nextTrackListNumber);
    }

    /**
     * Get number of tracklists.
     *
     * @return int
     */
    public static int getTrackListsNumber() {
        return prefs.getInt(TRACKLIST_NUMBER, 0);
    }

    /**
     * Get all tracklists stored in user preferences.
     *
     * @return List
     */
    public static ObservableList<TrackList> getAll() {

        ObservableList<TrackList> trackLists = FXCollections.observableArrayList();

        for(int index = 1; index <= getTrackListsNumber(); index++) {
            String listName = prefs.get(TRACKLIST_NAME + index, null);
            String listPath = prefs.get(TRACKLIST_PATH + index, null);
            TrackList trackList = new TrackList(index, listName, listPath);
            trackLists.add(trackList);
        }

        return trackLists;
    }

    /**
     * Remove all tracklists stored in user preferences.
     *
     */
    public static void deleteAll() {
        int trackListNumber = getTrackListsNumber();
        for (int index = 1; index <= trackListNumber; index++) {
            prefs.remove(TRACKLIST_NAME + index);
            prefs.remove(TRACKLIST_PATH + index);
            prefs.putInt(TRACKLIST_NUMBER, 0);
        }
        prefs.putInt(TRACKLIST_NUMBER, 0);
    }

    /**
     * Remove single tracklist.
     */
    public static void delete(TrackList trackList) {

        int trackListNumber = getTrackListsNumber();
        int id = trackList.getId();
        if (id <= 0 || id > trackListNumber) {
            return;
        }

        for (int index = id; index < trackListNumber; index++) {
            String nextName = prefs.get(TRACKLIST_NAME + (index + 1), null);
            String nextPath = prefs.get(TRACKLIST_PATH + (index + 1), null);
            if (nextName != null) {
                prefs.put(TRACKLIST_NAME + index, nextName);
            } else {
                prefs.remove(TRACKLIST_NAME + index);
            }
            if (nextPath != null) {
                prefs.put(TRACKLIST_PATH + index, nextPath);
            } else {
                prefs.remove(TRACKLIST_PATH + index);
            }
        }

        prefs.remove(TRACKLIST_NAME + trackListNumber);
        prefs.remove(TRACKLIST_PATH + trackListNumber);
        prefs.putInt(TRACKLIST_NUMBER, trackListNumber - 1);
    }

    /**
     * Refresh table columns util
     */
    public static void refreshList(ListView listView) {
        ObservableList<TrackList> items = getAll();
        listView.setItems(null);
        listView.setItems(items);
    }
}