module ckn.yakitori {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens ckn.yakitori.client.majang to javafx.graphics,javafx.fxml;
    opens ckn.yakitori.client.title to javafx.graphics,javafx.fxml;
    opens ckn.yakitori to javafx.fxml;
    exports ckn.yakitori;
}