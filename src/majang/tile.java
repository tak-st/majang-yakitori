package majang;

/**
 * 牌単体の情報を保持し、取得できるクラスです。
 *
 * <p>種類と数字、赤色がどうかの情報を持ち、そこから1sなどのFullName、ソート用のIDなどを取得可能です。</p>
 *
 * @version 1.1
 */
public class tile {
    /**
     * 種類 (m:萬子/p:筒子/s:索子/z:字牌)
     */
    private char category;
    /**
     * 数字(mps:1~9/z:1~7[東南西北発白中])
     */
    private int number;
    /**
     * 赤ドラかどうか
     */
    private boolean isRed;

    /**
     * デフォルトコンストラクタ
     * 入力値のチェックを行う
     *
     * @param category 種類 (m:萬子/p:筒子/s:索子/z:字牌)
     * @param number   数字(mps:1~9/z:1~7[東南西北発白中])
     * @param isRed    赤ドラかどうか
     * @throws IllegalArgumentException 種類が"mpsz"以外であるか、数字が0以下か10以上、zなら8以上の場合
     */
    public tile(char category, int number, boolean isRed) {
        int maxNum = 9;
        switch (category) {
            case 'z':
                // zならmaxは7
                maxNum = 7;
            case 's':
            case 'p':
            case 'm':
                this.category = category;
                break;
            default:
                throw new IllegalArgumentException("牌の種類が不正です。");
        }
        if (number >= 1 && number <= maxNum) {
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
        StringBuilder buf = new StringBuilder();
        buf.append(getNumber());
        buf.append(getCategory());
        return buf.toString();
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
     * @throws Exception あり得ない牌情報を持っている場合の例外
     * @since 1.0
     */
    public int getSortID() throws Exception {
        int num = 1;
        if (isRed()) {
            num = 0;
        }
        switch (getCategory()) {
            case 'm' -> {
                return getNumber() * 10 + num;
            }
            case 'p' -> {
                return 100 + getNumber() * 10 + num;
            }
            case 's' -> {
                return 200 + getNumber() * 10 + num;
            }
            default -> {
                return 300 + getNumber() * 10 + num;
            }
        }
    }

    /**
     * カテゴリーを取得します。
     *
     * @return char型の種類情報
     * @since 1.0
     */
    public char getCategory() {
        return category;
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
     * 赤ドラがどうかを取得します。
     *
     * @return boolean型の赤ドラ情報
     * @since 1.0
     */
    public boolean isRed() {
        return isRed;
    }


}
