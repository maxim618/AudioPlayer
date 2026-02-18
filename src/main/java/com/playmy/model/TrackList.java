package com.playmy.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a TrackList.
 */
public class TrackList {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty path = new SimpleStringProperty("");

    /**
     * Default constructor.
     */
    public TrackList() {
    }

    /**
     * Constructor with some initial data.
     */
    public TrackList(Integer id, String name, String path) {
        this.id.set(id == null ? 0 : id);
        this.name.set(name == null ? "" : name);
        this.path.set(path == null ? "" : path);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name == null ? "" : name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPath() {
        return path.get();
    }

    public void setPath(String path) {
        this.path.set(path == null ? "" : path);
    }

    public StringProperty pathProperty() {
        return path;
    }
    @Override
    public String toString() {
        return getName();
    }
}