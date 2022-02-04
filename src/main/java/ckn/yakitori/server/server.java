package ckn.yakitori.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class server extends Application {
    public static void main(String[] args) {
        Application.launch(server.class);
    }

    /**
     * クライアント用の画面を表示します。
     *
     * @param stage stage型の引数
     * @throws IOException resourcesフォルダにファイルが見つからなかった場合の例外
     */
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("server.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("麻雀サーバー");
        stage.setScene(scene);
        stage.show();
    }


}
