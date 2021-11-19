package ckn.yakitori.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * クライアント用の画面を表示するためのクラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public class client extends Application {

    /**
     * クライアント用の画面を表示します。
     *
     * @param stage stage型の引数
     * @throws IOException resourcesフォルダにファイルが見つからなかった場合の例外
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("practice.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("本格ネット麻雀 焼鳥");
        stage.setScene(scene);
        stage.show();
    }
}
