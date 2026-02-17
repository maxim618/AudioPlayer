package com.playmy.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.playmy.model.Track;
import com.playmy.model.TrackList;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

/**
 * Utilities to deal with Tracks.
 */
public class TrackUtil {

    public static final List<String> SUPPORTED_FILE_EXTENSIONS =
            Arrays.asList(".mp3");

    @FunctionalInterface
    public interface MediaFactory {
        Media create(String mediaUrl);
    }
    /**
     Get all tracklists stored in user preferences
    */
    public static ObservableList<Track> getAll(TrackList trackList,
                                               TableView trackTable) {
        return getAll(trackList, trackTable, Media::new);
    }

    public static ObservableList<Track> getAll(TrackList trackList,
                                               TableView trackTable,
                                               MediaFactory mediaFactory) {

        ObservableList<Track> tracks = FXCollections.observableArrayList();

        File dir = new File(trackList.getPath());
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Cannot find audio source directory: " + dir);
            return tracks;
        }

        String[] files = dir.list((d, name) ->
                SUPPORTED_FILE_EXTENSIONS.stream()
                        .anyMatch(name::endsWith));

        if (files == null) return tracks;

        for (String file : files) {

            File audioFile = new File(dir, file);

            Track track = new Track();
            track.setFileName(file);
            track.setPath(audioFile.getAbsolutePath());

            track.setTitle(file.replace(".mp3", ""));
            track.setArtist("Unknown");
            track.setAlbum("Unknown");
            track.setYear("Unknown");

            String mediaURL = audioFile.toURI().toString();
            Media media = mediaFactory.create(mediaURL);

            if (media != null) {
                media.getMetadata().addListener(
                        (Change<? extends String, ? extends Object> ch) -> {
                            if (ch.wasAdded()) {
                                handleMetadata(ch.getKey(),
                                        ch.getValueAdded(),
                                        track);

                                refreshTable(trackTable);
                            }
                        });
            }

            track.setMedia(media);
            tracks.add(track);
        }

        return tracks;
    }
    /**
     * Extract all metadata information
     */
    public static void handleMetadata(String key,
                                      Object value,
                                      Track track) {

        if (value == null) return;

        String val = value.toString().trim();

        switch (key) {

            case "album":
                track.setAlbum(val.isEmpty() ? "Unknown" : val);
                break;

            case "artist":
            case "album artist":
                track.setArtist(val.isEmpty() ? "Unknown" : val);
                break;

            case "title":
                track.setTitle(val.isEmpty()
                        ? track.getFileName().replace(".mp3", "")
                        : val);
                break;

            case "year":
                track.setYear(val.isEmpty() ? "Unknown" : val);
                break;

            case "image":
                track.setImage((Image) value);
                break;
        }
    }
    /**
     * Refresh table columns util
     */
    public static void refreshTable(TableView table) {
        for (Object colObj : table.getColumns()) {
            TableColumn<?, ?> column = (TableColumn<?, ?>) colObj;
            if (column.isVisible()) {
                column.setVisible(false);
                column.setVisible(true);
            }
        }
    }
    /**
     * Clean characters of URL
     */
    private static String cleanURL(String url) {
        url = url.replace("\\", "/");
        url = url.replaceAll(" ", "%20");
        url = url.replace("[", "%5B");
        url = url.replace("]", "%5D");
        return url;
    }
}