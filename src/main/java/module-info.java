/**
 * 必要なモジュールを記述しています。
 */
open module ckn.yakitori {
    requires transitive javafx.fxml;
    requires transitive javafx.controls;
    requires transitive javafx.web;
    requires transitive org.controlsfx.controls;
    requires transitive com.dlsc.formsfx;
    requires transitive org.kordamp.bootstrapfx.core;
    requires transitive eu.hansolo.tilesfx;

    exports ckn.yakitori;
}