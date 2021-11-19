package ckn.yakitori.client.controller;

import ckn.yakitori.server.mountainEntity;
import ckn.yakitori.share.hand;
import ckn.yakitori.share.mountain;
import ckn.yakitori.share.tile.designType;
import ckn.yakitori.share.tile.tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static ckn.yakitori.server.mountainType.YONMA;
import static ckn.yakitori.share.tile.designType.SUMIDA;

public class practiceController {
    //ガー by いたずらガチョウ
    public VBox VBox1;
    public VBox VBox2;
    public HBox DoraBox;
    public FlowPane Kawa;
    @FXML
    private Label Label1;
    @FXML
    private HBox HBox1;
    @FXML
    private HBox HBox2;


    @FXML
    protected void buttonClick(ActionEvent evt) {
        mountain Mountain = new mountainEntity(YONMA);
        hand Hand = new hand(Mountain);
        Hand.doPickTile(Mountain);
        Hand.sortTile();
        Label1.setText(Hand.toString());
        HBox1.getChildren().clear();
        for (tile Tile : Hand.getContents()) {
            HBox1.getChildren().add(setPaiImage(SUMIDA, Tile));
        }
        HBox1.getChildren().add(setPaiImage(SUMIDA, Hand.getPickTile()));

    }

    private ImageView setPaiImage(designType designType, tile Tile) {
        ImageView IV = new ImageView();
        IV.setImage(new Image(Tile.getImageUrl(designType)));
        IV.setFitWidth(HBox1.getHeight() * 0.75);
        IV.setFitHeight(HBox1.getHeight());
        return IV;
    }
}
