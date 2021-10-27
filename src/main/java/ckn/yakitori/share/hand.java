package ckn.yakitori.share;

import ckn.yakitori.share.tile.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 手牌の情報を保持し、操作を行うクラスです。
 *
 * <p>
 * TODO:鳴けるようにする
 * TODO:牌を入れ替えて、捨てられるようにする
 * </p>
 *
 * @author Shintani
 * @version 1.0
 */
public class hand {
    /**
     * 手牌の13個の牌
     */
    private final tile[] contents = new tile[13];
    /**
     * 最後にツモした牌／上がり牌
     */
    private tile pickTile;


    /**
     * Stringを使い１４個の牌を生成します。
     *
     * <p>
     * 1m2p3sというように数字+小文字の種類と指定するとその牌を生成できます。<br>
     * 123mというように同じ種類の場合省略した書き方も可能です。<br>
     * 5mrというようにrを付けると赤ドラとして生成されます。<br>
     * 最後に書かれた牌が当たり牌として扱われます。<br>
     * 最後の牌は省略せず5mrみたいな形で指定する必要があります。<br>
     * 14牌を超える文字列は無視されます。<br>
     * </p>
     *
     * @param string 手牌を示すString型の文字列
     * @since 1.0
     */
    public hand(String string) {
        int i = 0;
        int j = 0;
        ArrayList<Integer> numberTemp = new ArrayList<>();
        while (string.length() > i && j < 13) {

            switch (string.charAt(i)) {
                case 'm', 'p', 's', 'z' -> {
                    while (numberTemp.size() > 0) {
                        boolean isRed = false;
                        if (string.length() - i >= 2) {
                            isRed = string.charAt(i + 1) == 'r';
                        }
                        contents[j++] = new tile(string.charAt(i), numberTemp.get(0), isRed);
                        numberTemp.remove(0);
                        if (j == 13) {
                            break;
                        }
                    }
                }
                case '1', '2', '3', '4', '5', '6', '7', '8', '9' -> numberTemp.add(((int) (string.charAt(i))) - 48);
                case 'r' -> {

                }
                default -> throw new IllegalArgumentException(i + "文字目に、認識できない文字が含まれています。：" + string.charAt(i));
            }
            i++;
        }
        if (j == 13) {
            try {
                boolean isRed = false;
                if (string.length() - i >= 2) {
                    if (string.charAt(i) == 'r') {
                        i++;
                    }
                }
                if (string.length() - i >= 3) {
                    isRed = string.charAt(i + 2) == 'r';
                }
                this.pickTile = new tile(string.charAt(i + 1), string.charAt(i) - 48, isRed);
            } catch (IllegalArgumentException | StringIndexOutOfBoundsException ex) {
                throw new IllegalArgumentException("上がり牌が認識できませんでした。上がり牌は文字列の最後に追加する必要があります。");
            }
        } else {
            throw new IllegalArgumentException("認識できた牌が12牌以下でした。：" + j + "牌");
        }
    }

    /**
     * mountainから１３牌ツモって手牌にします。
     *
     * <p>
     * 14個目の牌はツモりませんのでdoPickTile()でツモってください。
     * </p>
     *
     * @param Mountain ツモる山を指定します。
     * @since 1.0
     */
    public hand(mountain Mountain) {
        for (int i = 0; i <= 12; i++) {
            this.contents[i] = Mountain.pickTile();
        }
    }

    /**
     * 指定された山から牌を引く（ツモる）動作を行います。
     *
     * <p>
     * 引いた牌はpickTileに格納されます。
     * </p>
     *
     * @param Mountain ツモる山を指定します。
     * @since 1.0
     */
    public void doPickTile(mountain Mountain) {
        this.pickTile = Mountain.pickTile();
    }

    /**
     * tile.getSortIDで返された値を使用し、昇順に牌を並び替えます。
     *
     * @since 1.0
     */
    public void sortTile() {
        Arrays.sort(contents, Comparator.comparingInt(tile::getSortID));
    }

    /**
     * 文字列型として解釈された際に、中身がわかりやすいように出力するメソッドです。
     *
     * <pre>
     *     hand{1m2m3m4m5m6m7m8m9m1s2s3s4s / 4s}
     * </pre>
     * <p>
     * みたいな形で返されます。
     * </p>
     *
     * @return handの中身を示すString型の文字列
     * @since 1.0
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("hand{");
        Arrays.stream(getContents()).forEach(t -> sb.append(t.getFullName(true)));
        return sb.append(" / ").append(getPickTile().getFullName(true)).append("}").toString();
    }

    /**
     * １３牌が格納されている配列を返します。
     *
     * @return 手牌が格納されているtile型の配列
     * @since 1.0
     */
    public tile[] getContents() {
        return contents;
    }

    /**
     * 最後にツモした牌／上がり牌を返します。
     *
     * @return 最後にツモした牌／上がり牌を示すtile型
     * @since 1.0
     */
    public tile getPickTile() {
        return pickTile;
    }

}
