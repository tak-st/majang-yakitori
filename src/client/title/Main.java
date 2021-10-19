package client.title;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("title.fxml"));
        primaryStage.setTitle("本格ネット麻雀 焼鳥");
        primaryStage.setScene(new Scene(root, 600, 550));
        primaryStage.show();
    }
}
