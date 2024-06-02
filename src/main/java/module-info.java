module br.SandyLemos.LyricsSearch {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.gluonhq.charm.glisten;
    requires org.apache.httpcomponents.client5.httpclient5;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires org.json;

    //requires org.controlsfx.controls;
    //requires com.dlsc.formsfx;
    //requires net.synedra.validatorfx;
    //requires org.kordamp.ikonli.javafx;
    //requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    //requires com.almasb.fxgl.all;

    opens br.sandy.lyricsSearch to javafx.fxml;
    exports br.sandy.lyricsSearch;
    exports br.sandy.lyricsSearch.Controller;
    opens br.sandy.lyricsSearch.Controller to javafx.fxml;
}