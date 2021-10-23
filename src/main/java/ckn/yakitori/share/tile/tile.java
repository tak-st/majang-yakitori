package ckn.yakitori.share.tile;

import static ckn.yakitori.share.tile.tileType.*;

/**
 * 牌単体の情報を保持し、取得できるクラスです。
 *
 * <p>種類と数字、赤色がどうかの情報を持ち、そこから1sなどのFullName、ソート用のIDなどを取得可能です。</p>
 *
 * @author Shintani
 * @version 1.5
 */
public class tile {
    /**
     * 種類 (m:萬子/p:筒子/s:索子/z:字牌)
     */
    private tileType category;
    /**
     * 数字(mps:1~9/z:1~7[東南西北発白中])
     */
    private final int number;
    /**
     * 赤ドラかどうか
     */
    private final boolean isRed;

    /**
     * カテゴリーがchar型で入力された場合、tileType型に変換
     * 入力値のチェックを行う
     *
     * @param category 種類 (m:萬子/p:筒子/s:索子/z:字牌)
     * @param number   数字(mps:1~9/z:1~7[東南西北発白中])
     * @param isRed    赤ドラかどうか
     * @throws IllegalArgumentException 種類が"m/p/s/z"以外であるか、数字が0以下か10以上、zなら8以上の場合
     * @since 1.0
     */
    public tile(char category, int number, boolean isRed) {
        int maxNum = 9;
        switch (category) {
            case 'z' -> {
                // zならmaxは7
                maxNum = 7;
                this.category = ZIPAI;
            }
            case 's' -> this.category = SOHZU;
            case 'p' -> this.category = PINZU;
            case 'm' -> this.category = MANZU;
            default -> throw new IllegalArgumentException("牌の種類が不正です。");
        }
        if (number >= 1 && number <= maxNum) {
            if (this.category == ZIPAI) {
                if (number <= 4) {
                    this.category = FONPAI;
                } else {
                    this.category = SANGEN;
                    number = number - 4;
                }
            }
            this.number = number;
        } else {
            throw new IllegalArgumentException("牌の数字が不正です。");
        }
        this.isRed = isRed;
    }

    /**
     * カテゴリーがtileType型で入力された場合
     * 入力値のチェックを行う
     *
     * @param category 種類 (MANZU:萬子/PINZU:筒子/SOHZU:索子/ZIPAI:字牌/FONPAI:風牌/SANGEN:三元牌)
     * @param number   (萬子筒子索子:1~9/字牌:1~7[東南西北発白中]/風牌:1~4[東南西北]/三元牌:1~3[白発中])
     * @param isRed    赤ドラかどうか
     * @throws IllegalArgumentException 種類・数字が各種類に適した範囲以外の場合
     * @since 1.2
     */
    public tile(tileType category, int number, boolean isRed) {
        this.category = category;
        int maxNum;
        switch (category) {
            case MANZU, PINZU, SOHZU -> maxNum = 9;
            case ZIPAI -> maxNum = 7;
            case FONPAI -> maxNum = 4;
            case SANGEN -> maxNum = 3;
            default -> throw new IllegalArgumentException("予期しない牌の種類です。");
        }
        if (number >= 1 && number <= maxNum) {
            if (this.category == ZIPAI) {
                if (number <= 4) {
                    this.category = FONPAI;
                } else {
                    this.category = SANGEN;
                    number = number - 4;
                }
            }
            this.number = number;
        } else {
            throw new IllegalArgumentException("牌の数字が不正です。");
        }
        this.isRed = isRed;
    }

    /**
     * 1m,4p,7sという形で牌を表す文字列を取得できます。
     *
     * @return String型の牌種を表す文字列
     * @since 1.0
     */
    public String getFullName() {
        return String.valueOf(getNumberOld()) + getCategoryChar();
    }

    /**
     * 1m,4p,7sという形で牌を表す文字列を取得できます。
     *
     * @param redInfo trueを指定すると赤ドラの場合に最後にrが付与されます
     * @return String型の牌種を表す文字列
     * @since 1.5
     */
    public String getFullName(boolean redInfo) {

        if (redInfo) {
            return getFullName() + (isRed ? "r" : "");
        } else {
            return getFullName();
        }
    }

    /**
     * 牌が幺九牌かどうかを取得できます。
     *
     * @return Boolean型の幺九牌かどうかの情報
     * @since 1.3
     */
    public boolean getYaochu() {
        if (getCategoryChar() == 'z') {
            return true;
        } else return getNumber() == 1 || getNumber() == 9;
    }


    /**
     * 手牌を並べる際に使用するソートIDを取得します。
     *
     * <p>
     * 百の桁:萬子:0,筒子:1,索子:2
     * 十の桁:数字
     * 一の桁:赤ドラなら0 そうでないなら1 赤ドラを左にするためのデータ。
     * </p>
     *
     * @return int型のソートID
     * @since 1.0
     */
    public int getSortID() {
        int num = 1;
        if (isRed()) {
            num = 0;
        }
        switch (getCategoryChar()) {
            case 'm' -> {
                return getNumberOld() * 10 + num;
            }
            case 'p' -> {
                return 100 + getNumberOld() * 10 + num;
            }
            case 's' -> {
                return 200 + getNumberOld() * 10 + num;
            }
            default -> {
                return 300 + getNumberOld() * 10 + num;
            }
        }
    }

    /**
     * tileType型でカテゴリーを取得します。
     *
     * @return char型の種類情報
     * @since 1.2
     */
    public tileType getCategory() {
        return category;
    }

    /**
     * char型でカテゴリーを取得します。
     *
     * @return char型の種類情報
     * @since 1.0
     */
    public char getCategoryChar() {
        char $result;
        switch (category) {
            case MANZU -> $result = 'm';
            case PINZU -> $result = 'p';
            case SOHZU -> $result = 's';
            default -> $result = 'z';
        }
        return $result;
    }

    /**
     * 数字を取得します。
     *
     * @return int型の数字情報
     * @since 1.0
     */
    public int getNumber() {
        return number;
    }

    /**
     * 三元牌を字牌という括りで処理するための数字を取得します。
     *
     * @return int型の数字情報
     * @since 1.2
     */
    public int getNumberOld() {
        if (category == SANGEN) {
            return number + 4;
        }
        return number;
    }

    /**
     * 赤ドラがどうかを取得します。
     *
     * @return boolean型の赤ドラ情報
     * @since 1.0
     */
    public boolean isRed() {
        return isRed;
    }


    /**
     * toStringを使用した際に牌情報を返します。
     *
     * @return 2p,5srなどの牌情報を示す文字列
     * @since 1.4
     */
    @Override
    public String toString() {
        return "tile{" +
                getFullName(true) +
                '}';
    }
}
