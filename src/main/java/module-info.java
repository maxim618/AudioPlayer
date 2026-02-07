module com.playmy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.prefs;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome;

    exports com.playmy;
    exports com.playmy.util;
    exports com.playmy.model;
    exports com.playmy.view;

    opens com.playmy.view to javafx.fxml;
}
