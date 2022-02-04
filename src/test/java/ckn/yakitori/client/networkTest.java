package ckn.yakitori.client;

import ckn.yakitori.share.mountain;
import ckn.yakitori.share.tile.tile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class networkTest extends ApplicationTest {
    TextField input;

    @Override
    public void start(final Stage stage) throws Exception {
        if (Boolean.getBoolean("headless")) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
        final FXMLLoader loader
                = new FXMLLoader(getClass().getClassLoader().getResource("server.fxml"));
        final Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        input = lookup("#command").query();
    }

    @AfterAll
    void tearDown() {
        Platform.runLater(() -> input.setText("quit"));
        Platform.runLater(() -> input.fireEvent(new ActionEvent()));

    }

    @Test
    void connect() {
        int i = 0;
        network Network = new network("localhost");
        Network.SendString("TestNow");
        assertEquals("TestNow", Network.getReceive());
        Network.SendString("mountainClear");
        assertEquals("山を初期化しました。", Network.getReceive(), "山の初期化に失敗");
        Network.SendString("createSequenceMountain");
        assertEquals("作成しました。", Network.getReceive(), "山の作成に失敗");
        Network.SendString("doPickTile");
        assertEquals(new tile("1m").getFullName(), Network.getReceiveTile().getFullName());
        mountain Mountain = new mountainNetwork(Network);
        assertEquals(135, Mountain.getRemaingTile());
        assertEquals("1m", Mountain.pickTile().getFullName());
        assertEquals(134, Mountain.getRemaingTile());
        Network.quit();
    }
}