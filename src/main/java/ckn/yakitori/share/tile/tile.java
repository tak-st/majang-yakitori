package ckn.yakitori.share.tile;

import java.io.Serializable;

import static ckn.yakitori.share.tile.tileType.*;

/**
 * 牌単体の情報を保持し、取得できるクラスです。
 *
 * <p>種類と数字、赤色がどうかの情報を持ち、そこから1sなどのFullName、ソート用のIDなどを取得可能です。</p>
 *
 * @author Shintani
 * @version 1.9
 */
public class tile implements Serializable {
    /**
     * 種類
     */
    private tileType category;
    /**
     * 数字(mps:1~9/z:1~7[東南西北発白中]/e:0)
     */
    private int number;
    /**
     * 赤ドラかどうか
     */
    private boolean isRed;
    /**
     * この牌が上がり牌がどうか
     */
    private boolean isWinTile;

    /**
     * カテゴリーがchar型で入力された場合のコンストラクタ。
     * tileType型に変換、入力値のチェックを行う
     *
     * @param category 種類 (m:萬子/p:筒子/s:索子/z:字牌)
     * @param number   数字(mps:1~9/z:1~7[東南西北発白中])
     * @param isRed    赤ドラかどうか
     * @throws IllegalArgumentException 種類が"m/p/s/z"以外であるか、数字が0以下か10以上、zなら8以上の場合
     * @since 1.0
     */
    public tile(char category, int number, boolean isRed) {
        setParameterChar(category, number, isRed);
    }

    /**
     * カテゴリーがtileType型で入力された場合のコンストラクタ。
     * 入力値のチェックを行う
     *
     * @param category 種類 (MANZU:萬子/PINZU:筒子/SOHZU:索子/ZIPAI:字牌/FONPAI:風牌/SANGEN:三元牌)
     * @param number   (萬子筒子索子:1~9/字牌:1~7[東南西北発白中]/風牌:1~4[東南西北]/三元牌:1~3[白発中])
     * @param isRed    赤ドラかどうか
     * @throws IllegalArgumentException 種類・数字が各種類に適した範囲以外の場合
     * @since 1.2
     */
    public tile(tileType category, int number, boolean isRed) {
        setParameter(category, number, isRed);
    }

    /**
     * 文字列が渡された場合のコンストラクタ。
     *
     * @param string 牌を示す文字列
     * @throws IllegalArgumentException 牌を示す情報が不正な時の例外
     * @since 1.6
     */
    public tile(String string) {
        int number = -1;
        char category = 'n';
        boolean isRed = false;
        for (int i = 0; i < string.length(); i++) {
            switch (string.charAt(i)) {
                case '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                    if (number == -1) {
                        number = ((int) (string.charAt(i))) - 48;
                    } else {
                        throw new IllegalArgumentException("数字が2つ以上存在します。");
                    }
                }
                case 's', 'p', 'm', 'z', 'e' -> {
                    if (category == 'n') {
                        category = string.charAt(i);
                        if (category == 'e') {
                            number = 0;
                        }
                    } else {
                        throw new IllegalArgumentException("種類を示す文字が2つ以上存在します。");
                    }
                }
                case 'r' -> isRed = true;
                default -> throw new IllegalArgumentException(i + 1 + "文字目に、認識できない文字が含まれています。：" + string.charAt(i));
            }
        }
        if (number == -1 || category == 'n') {
            throw new IllegalArgumentException("牌を設定するための情報が不足しています。");
        }
        setParameterChar(category, number, isRed);
    }

    /**
     * パラメータをセットするメソッド。
     * <p>
     * コンストラクタによって呼び出される。
     *
     * @param category 種類 (MANZU:萬子/PINZU:筒子/SOHZU:索子/ZIPAI:字牌/FONPAI:風牌/SANGEN:三元牌)
     * @param number   (萬子筒子索子:1~9/字牌:1~7[東南西北発白中]/風牌:1~4[東南西北]/三元牌:1~3[白発中])
     * @param isRed    赤ドラかどうか
     * @throws IllegalArgumentException 種類・数字が各種類に適した範囲以外の場合
     * @since 1.6
     */
    private void setParameter(tileType category, int number, boolean isRed) {
        this.category = category;
        setNumber(number, category.getMaxNum());
        if (category != EMPTY) {
            this.isRed = isRed;
        }
    }

    private void setNumber(int number, int maxNum) {
        if (getCategory() == EMPTY) {
            this.number = 0;
            return;
        }
        if (number >= 1 && number <= maxNum) {
            if (getCategory() == ZIPAI) {
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
    }

    /**
     * 種類をChar型で、パラメータをセットするメソッド。
     * <p>
     * コンスラクタによって呼び出される。
     *
     * @param category 種類 (m:萬子/p:筒子/s:索子/z:字牌)
     * @param number   数字(mps:1~9/z:1~7[東南西北発白中])
     * @param isRed    赤ドラかどうか
     * @throws IllegalArgumentException 種類が"m/p/s/z"以外であるか、数字が0以下か10以上、zなら8以上の場合
     * @since 1.6
     */
    private void setParameterChar(char category, int number, boolean isRed) {
        switch (category) {
            case 'z' -> this.category = ZIPAI;
            case 's' -> this.category = SOHZU;
            case 'p' -> this.category = PINZU;
            case 'm' -> this.category = MANZU;
            case 'e' -> {
                this.category = EMPTY;
                isRed = false;
            }
            default -> throw new IllegalArgumentException("牌の種類が不正です。");
        }
        setNumber(number, getCategory().getMaxNum());
        this.isRed = isRed;
    }

    /**
     * 1m,4p,7sという形で牌を表す文字列を取得できます。
     *
     * @return String型の牌種を表す文字列
     * @since 1.0
     */
    public String getFullName() {
        if (getCategory() == EMPTY) {
            return "";
        }
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
     * 百の桁:萬子:0,筒子:1,索子:2,字牌:3 <br>
     * 十の桁:数字 <br>
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
            case 'z' -> {
                return 300 + getNumberOld() * 10 + num;
            }
            default -> {
                return 999;
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
            case EMPTY -> $result = 'e';
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
     * 牌のImageのUrlを取得するメソッドです。
     *
     * @param designType 牌デザインを指定してください。
     * @return ImageのUrlを返します。
     * @since 1.9
     */
    public String getImageUrl(designType designType) {
        return "tileSet/" + (isRed() ? designType.getUrlRed() : designType.getUrlNormal()) + "/" + getFullName() + ".png";
    }

    /**
     * toStringを使用した際に牌情報を返します。
     *
     * @return 2p, 5srなどの牌情報を示す文字列
     * @since 1.4
     */
    @Override
    public String toString() {
        return "tile{" +
                getFullName(true) + (isWinTile() ? "_" : "") +
                '}';
    }

    /**
     * 上がり牌がどうかを返します。
     *
     * @return 上がり牌かどうか
     * @since 1.7
     */
    public boolean isWinTile() {
        return isWinTile;
    }

    /**
     * 上がり牌というフラグをセットします。
     *
     * @param winTile 上がり牌であるかどうか
     * @since 1.7
     */
    public void setWinTile(boolean winTile) {
        isWinTile = winTile;
    }
}
