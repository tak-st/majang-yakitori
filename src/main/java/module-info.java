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

    exports ckn.yakitori;
    exports ckn.yakitori.server;
    exports ckn.yakitori.client;
}