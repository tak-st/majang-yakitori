/**
 * 必要なモジュールを記述しています。
 */
open module ckn.yakitori {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.jetbrains.annotations;
    requires java.datatransfer;
    requires java.desktop;
    requires org.testfx.junit5;

    exports ckn.yakitori;
    exports ckn.yakitori.server;
    exports ckn.yakitori.client;
}