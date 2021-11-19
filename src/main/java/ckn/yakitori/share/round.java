package ckn.yakitori.share;

import ckn.yakitori.server.deadTile;
import ckn.yakitori.server.mountainEntity;
import ckn.yakitori.server.mountainType;

public class round {

    int turn = 0;

    // 各インスタンス用
    mountain mountain_tile;
    hand Hand;
    deadTile Dead_tile;

    public void roundStart(rule Rule) {

        //何順目か
        turn += 1;

        //三麻か四麻の判定
        if (Rule.isSanma) {
            mountain_tile = new mountainEntity(mountainType.SANMA);
        } else {
            mountain_tile = new mountainEntity(mountainType.YONMA);
        }

        //各プレイヤーに手配を配る
        //TODO マッチクラス作成時に手牌を配る処理を作成
        Hand = new hand(mountain_tile);

        //王牌を作成する。
        Dead_tile = new deadTile(mountain_tile);
    }
    //現在誰の番か返す関数を作成　modで求めるなど
}
