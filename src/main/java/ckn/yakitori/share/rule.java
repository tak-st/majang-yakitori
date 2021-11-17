package ckn.yakitori.share;

/**
 * ルールの情報を所持するクラスです。
 *
 * @author mizu
 * @version 1.0
 */
public class rule {

    /**
     * 対局の長さです。
     */
    int matchLongth = 8;

    /**
     * 四麻か三麻の判定用です。
     */
    boolean isSanma = false;

    /**
     * 各プレイヤーの初期持ち点です。
     */
    int startScore = 25000;

    /**
     * 勝利に必要な点数です。
     */
    int winNeedScore = 30000;

    /**
     * 赤ドラの数です。
     */
    int red = 3;

    /**
     * 喰いタンできるかどうかの判定用です。
     */
    boolean canKuitan = true;

    /**
     * 上がりに何翻必要かの数です。
     */
    int needHan = 1;

    public int getMatchLongth() {
        return matchLongth;
    }

    public void setMatchLongth(int matchLongth) {
        this.matchLongth = matchLongth;
    }

    public boolean isSanma() {
        return isSanma;
    }

    public void setSanma(boolean sanma) {
        isSanma = sanma;
    }

    public int getStartScore() {
        return startScore;
    }

    public void setStartScore(int startScore) {
        this.startScore = startScore;
    }

    public int getWinNeedScore() {
        return winNeedScore;
    }

    public void setWinNeedScore(int winNeedScore) {
        this.winNeedScore = winNeedScore;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public boolean isCanKuitan() {
        return canKuitan;
    }

    public void setCanKuitan(boolean canKuitan) {
        this.canKuitan = canKuitan;
    }

    public int getNeedHan() {
        return needHan;
    }

    public void setNeedHan(int needHan) {
        this.needHan = needHan;
    }
}
