/**
 * 必要なモジュールを記述しています。
 */
module ckn.yakitori {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.jetbrains.annotations;

    opens ckn.yakitori.client.controller to javafx.graphics, javafx.fxml;
    opens ckn.yakitori.client to javafx.graphics, javafx.fxml;
    opens ckn.yakitori to javafx.fxml;
    exports ckn.yakitori;
}