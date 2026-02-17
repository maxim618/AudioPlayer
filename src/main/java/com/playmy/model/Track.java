package com.playmy.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

/**
 * Model class for a Track.
 *
 */
public class Track {

    private final StringProperty fileName = new SimpleStringProperty("");
    private final StringProperty path = new SimpleStringProperty("");
    private final StringProperty album = new SimpleStringProperty("Unknown");
    private final StringProperty artist = new SimpleStringProperty("Unknown");
    private final StringProperty title = new SimpleStringProperty("");
    private final StringProperty year = new SimpleStringProperty("");
    private final StringProperty author = new SimpleStringProperty("");
    private Media media;
    private Image image;

    /**
     * Default constructor.
     */
    public Track() {
        this(null, null, null);
    }

    /**
     * Constructor with some initial data.
     */
    public Track(String fileName, String filePath, Media media) {
        this.fileName.set(fileName);
        this.path.set(filePath);
        this.media = media;
    }


    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName.get();
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public StringProperty fileNameProperty() {
        return fileName;
    }

    /**
     * @return the media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * @param media the media to set
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    public String getPath() {
        return path.get();
    }

    public void setPath(String value) {
        path.set(value);
    }

    public StringProperty pathProperty() {
        return path;
    }

    /**
     * @return album
     */
    public String getAlbum() {
        return album.get();
    }

    /**
     * @param album the album to set
     */
    public void setAlbum(String album) {
        this.album.set(album == null || album.isBlank() ? "Unknown" : album);
    }

    public StringProperty albumProperty() {
        return album;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist.get();
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist.set(artist == null || artist.isBlank() ? "Unknown" : artist);
    }

    public StringProperty artistProperty() {
        return artist;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title.set(title == null || title.isBlank() ? fileName.get() : title);
    }
    public StringProperty titleProperty() {
        return title;
    }

    public void setAuthor (String author) {
        this.author.set(author == null || author.isBlank() ? "Unknown" : author);
    }

    public StringProperty authorProperty() {
        return author;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year.get();
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year.set(year);
    }

    public StringProperty yearProperty() {
        return year;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

}
