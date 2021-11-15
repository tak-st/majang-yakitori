package ckn.yakitori.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * クライアント用の画面を表示するためのクラスです。
 *
 * @author Shintani
 * @version 1.0
 */
public class client extends Application {

    /**
     * アプリケーション実行時に処理されるメソッドです。
     *
     * @param args 起動時に渡される引数
     */
    public static void main(String[] args) {
        Application.launch(client.class);
    }

    /**
     * クライアント用の画面を表示します。
     *
     * @param stage stage型の引数
     * @throws IOException resourcesフォルダにファイルが見つからなかった場合の例外
     */
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("title.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("本格ネット麻雀 焼鳥");
        stage.setScene(scene);
        stage.show();
    }
}
