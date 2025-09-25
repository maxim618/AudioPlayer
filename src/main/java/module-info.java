module com.trackio {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.prefs;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome;

    exports com.trackio;
    exports com.trackio.util;
    exports com.trackio.model;
    exports com.trackio.view;

    opens com.trackio.view to javafx.fxml;
}
