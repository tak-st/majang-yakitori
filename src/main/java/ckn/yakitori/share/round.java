package ckn.yakitori.share;

import ckn.yakitori.server.deadTile;
import ckn.yakitori.server.mountainEntity;
import ckn.yakitori.server.mountainType;

/**
 * 各ラウンド開始時に行う処理です。
 *
 * @author mizu
 * @version 1.0
 */
public class round {

    //何順目か
    int turn = 0;

    /**
     * mountain:山を生成する用<br>
     * hand:各プレイヤーに手配を配る用<br>
     * deadTile:王牌を作成する用<br>
     */
    mountain mountain_tile;
    hand Hand;
    deadTile Dead_tile;

    /**
     * 各ラウンド開始時に行う処理です。<br><br>
     * 三麻か四麻を判定し手配作成。<br>
     * (未完成)作成された手配を各プレイヤーに配布。<br>
     * 王配作成。
     *
     * @param Rule 三麻か四麻かを判定する為に使用します。
     */
    public void roundStart(rule Rule) {

        turn += 1;

        //三麻か四麻の判定
        if (Rule.isSanma) {
            mountain_tile = new mountainEntity(mountainType.SANMA);
        } else {
            mountain_tile = new mountainEntity(mountainType.YONMA);
        }

        //各プレイヤーに手配を配布
        //TODO マッチクラス作成時に手牌を配る処理を作成
        Hand = new hand(mountain_tile);

        //王牌を作成する。
        Dead_tile = new deadTile(mountain_tile);
    }

    /**
     * 三麻か四麻を判定し、現在誰の順目かを返す関数です。
     *
     * @param Rule 三麻か四麻かを判定する為に使用します。
     * @return 現在が誰の順目かを返します。
     */
    public int getPlayer(rule Rule) {
        int playerTurn;
        if (Rule.isSanma) {
            switch (turn % 3) {
                case 0 -> playerTurn = 3;
                case 1 -> playerTurn = 1;
                default -> playerTurn = 2;
            }
        } else {
            switch (turn % 4) {
                case 0 -> playerTurn = 4;
                case 1 -> playerTurn = 1;
                case 2 -> playerTurn = 2;
                default -> playerTurn = 3;
            }
        }
        // 確認用
        // System.out.println(playerTurn);
        return playerTurn;
    }
}
