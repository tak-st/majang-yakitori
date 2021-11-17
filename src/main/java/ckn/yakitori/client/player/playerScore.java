package ckn.yakitori.client.player;

/**
 * プレイヤーの持ち点変動の処理です。
 *
 * @author mizu
 * @version 1.0
 */
public class playerScore {

    /**
     * プレイヤーの持ち点です。
     */
    int player_score;

    /**
     * @param num 変動させる点数です。
     */
    public void changeScore(int num) {
        this.player_score += num;
    }

    public int getPlayer_score() {
        return player_score;
    }

    /**
     * @param score 各プレイヤーの初期持ち点を設定します。
     */
    public void setPlayer_score(int score) {
        this.player_score = score;
    }
}