package ckn.yakitori.share;

import ckn.yakitori.share.mentsu.kantsu;
import ckn.yakitori.share.mentsu.kotsu;
import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.mentsu.shuntsu;
import ckn.yakitori.share.tile.tile;

import java.util.*;

import static ckn.yakitori.share.tile.tileType.EMPTY;

/**
 * 手牌の情報を保持し、操作を行うクラスです。
 *
 * <p>
 * TODO:牌を入れ替えて、捨てられるようにする
 * </p>
 *
 * @author Shintani
 * @version 1.1
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
    private final List<mentsu> fuuroMentsuList = new ArrayList<>();


    /**
     * Stringを使い１４個の牌を生成します。
     *
     * <ul>
     *  <li>1m2p3sというように数字+小文字の種類と指定するとその牌を生成できます。</li>
     *  <li>123mというように同じ種類の場合省略した書き方も可能です。</li>
     *  <li>5mrというようにrを付けると赤ドラとして生成されます。</li>
     *  <li>最後に書かれた牌が当たり牌として扱われます。</li>
     *  <li>最後の牌は省略せず5mrみたいな形で指定する必要があります。</li>
     *  <li>14牌を超える文字列は無視されます。<br>
     * </ul>
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
     * ポンを行うメソッドです。
     *
     * @param ponTile ポンする時に使用する外部の牌
     * @since 1.1
     */
    public void doPon(tile ponTile) {
        sortTile();
        int count = 0;
        tile Tile1 = null;
        tile Tile2 = null;
        for (int i = 0; i < getContents().length; i++) {
            if (Objects.equals(getContents()[i].getFullName(), ponTile.getFullName())) {
                if (count == 0) {
                    Tile1 = getContents()[i];
                } else if (count == 1) {
                    Tile2 = getContents()[i];
                }
                count++;
            }
        }
        if (count >= 2) {
            fuuroMentsuList.add(new kotsu(true, Tile1, Tile2, ponTile));
            count = 2;
            for (int i = 0; i < getContents().length; i++) {
                if (Objects.equals(getContents()[i].getFullName(), ponTile.getFullName())) {
                    if (count > 0) {
                        setContents(new tile("e"), i);
                        count--;
                        i = -1;
                        sortTile();
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("その牌ではポンできません。: " + ponTile);
        }
    }

    /**
     * 明カンを行うメソッドです。
     *
     * @param kanTile カンする時に使用する外部の牌
     * @since 1.1
     */
    public void doLightKan(tile kanTile) {
        sortTile();
        int count = 0;
        tile Tile1 = null;
        tile Tile2 = null;
        tile Tile3 = null;
        for (int i = 0; i < getContents().length; i++) {
            if (Objects.equals(getContents()[i].getFullName(), kanTile.getFullName())) {
                if (count == 0) {
                    Tile1 = getContents()[i];
                } else if (count == 1) {
                    Tile2 = getContents()[i];
                } else if (count == 2) {
                    Tile3 = getContents()[i];
                }
                count++;
            }
        }
        if (count >= 3) {
            fuuroMentsuList.add(new kantsu(true, Tile1, Tile2, Tile3, kanTile));
            count = 3;
            for (int i = 0; i < getContents().length; i++) {
                if (Objects.equals(getContents()[i].getFullName(), kanTile.getFullName())) {
                    if (count > 0) {
                        setContents(new tile("e"), i);
                        count--;
                        i = -1;
                        sortTile();
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("その牌ではカンできません。: " + kanTile);
        }
    }

    /**
     * 暗カンを行うメソッドです。
     *
     * @param index カンする時に使用する手牌の中の牌の番号
     * @since 1.1
     */
    public void doDarkKan(int index) {
        sortTile();
        int count = 0;
        tile Tile1 = null;
        tile Tile2 = null;
        tile Tile3 = null;
        tile Tile4 = null;
        tile kanTile = getAll()[index];
        for (int i = 0; i < getContents().length; i++) {
            if (Objects.equals(getContents()[i].getFullName(), kanTile.getFullName())) {
                if (count == 0) {
                    Tile1 = getContents()[i];
                } else if (count == 1) {
                    Tile2 = getContents()[i];
                } else if (count == 2) {
                    Tile3 = getContents()[i];
                } else if (count == 3) {
                    Tile4 = getContents()[i];
                }
                count++;
            }
        }
        if (count >= 4) {
            fuuroMentsuList.add(new kantsu(false, Tile1, Tile2, Tile3, Tile4));
            count = 4;
            for (int i = 0; i < getContents().length; i++) {
                if (Objects.equals(getContents()[i].getFullName(), kanTile.getFullName())) {
                    if (count > 0) {
                        setContents(new tile("e"), i);
                        count--;
                        i = -1;
                        sortTile();
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("その牌ではカンできません。: " + kanTile);
        }
    }

    /**
     * 加カンを行うメソッドです。
     *
     * @param index カンする時に使用する手牌の中の牌の番号
     * @since 1.1
     */
    public void doAddKan(int index) {
        tile kanTile = getAll()[index];
        for (mentsu Mentsu : fuuroMentsuList) {
            if (Mentsu instanceof kotsu) {
                if (Objects.equals(Mentsu.getIdentifierTile().getFullName(), kanTile.getFullName())) {
                    fuuroMentsuList.add(new kantsu((kotsu) Mentsu, kanTile));
                    fuuroMentsuList.remove(Mentsu);
                    setContents(new tile("e"), index);

                    sortTile();
                    return;
                }
            }
        }
        throw new IllegalArgumentException("その牌ではカンできません。: " + kanTile);
    }

    /**
     * チーを行うメソッドです。
     *
     * @param chiiTile   チーする時に使用する外部の牌
     * @param tile1Index チーを行う牌1枚目、番号で指定
     * @param tile2Index チーを行う牌2枚目、番号で指定
     */
    public void doChii(tile chiiTile, int tile1Index, int tile2Index) {
        mentsu Shuntsu = new shuntsu(true, getAll()[tile1Index], getAll()[tile2Index], chiiTile);
        if (Shuntsu.isCheckPass()) {
            fuuroMentsuList.add(Shuntsu);
            setContents(new tile("e"), tile1Index);
            setContents(new tile("e"), tile2Index);

            sortTile();
        } else {
            throw new IllegalArgumentException("その組み合わせではチーできません。");
        }
    }

    /**
     * 文字列型として解釈された際に、中身がわかりやすいように出力するメソッドです。
     * <p></p>
     * <pre>
     *     手牌{1m2m3m4m5m6m7m8m9m1s2s3s4s / 4s}
     * </pre>
     * <p>みたいな形で返されます。</p>
     * <p>副露している面子があればそれも表示します。</p>
     *
     * @return handの中身を示す文字列（String型）
     * @since 1.0
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("手牌{");
        Arrays.stream(getContents()).forEach(t -> sb.append(t.getFullName(true)));
        if (fuuroMentsuList.size() == 0) {
            return sb.append(" / ").append(getPickTile().getFullName(true)).append("}").toString();
        } else {
            return sb.append(" / ").append(getPickTile().getFullName(true)).append(" < ").append(fuuroMentsuList).append(" >").append("}").toString();

        }

    }

    /**
     * １３牌が格納されている配列を空白牌を除外して返します。
     *
     * @return 手牌 （tile型の配列）
     * @since 1.0
     */
    public tile[] getContents() {
        return getContents(false);
    }

    /**
     * １３牌が格納されている配列を返します。引数で空白牌を含めるか選択できます。
     *
     * @param includeEmpty 空白を含めるかどうか
     * @return 手牌 （tile型の配列）
     * @since 1.1
     */
    public tile[] getContents(boolean includeEmpty) {
        if (includeEmpty) {
            System.out.println(Arrays.toString(contents));
            return contents;
        } else {
            List<tile> result = new ArrayList<>();
            for (tile Tile : contents) {
                if (Tile.getCategory() != EMPTY) {
                    result.add(Tile);
                }
            }
            return result.toArray(new tile[0]);
        }
    }

    /**
     * 手牌の何番目かに牌をセットするメソッドです。
     *
     * @param Tile  セットしたい牌
     * @param Index 手牌の何番目か
     * @since 1.1
     */
    public void setContents(tile Tile, int Index) {

        if (getContents().length == Index) {
            pickTile = Tile;
            return;
        }
        contents[Index] = Tile;
    }

    /**
     * 最後にツモした牌／上がり牌を返します。
     *
     * @return 最後にツモした牌／上がり牌 （tile型）
     * @since 1.0
     */
    public tile getPickTile() {
        return pickTile;
    }

    /**
     * 手牌＋ツモ牌／上がり牌の１４牌全てを配列で返します。空白牌を除外します。
     *
     * @return 手牌＋ツモ牌／上がり牌（tile型の配列）
     * @since 1.1
     */
    public tile[] getAll() {
        return getAll(false);
    }

    /**
     * 手牌＋ツモ牌／上がり牌の１４牌全てを配列で返します。空白牌を含めるかどうかを引数で指定可能。
     *
     * @param includeEmpty 空白牌を含めるかどうか
     * @return 手牌＋ツモ牌／上がり牌（tile型の配列）
     * @since 1.1
     */
    public tile[] getAll(boolean includeEmpty) {
        if (includeEmpty) {
            tile[] All = new tile[14];
            int i = 0;
            for (tile Tile : getContents(includeEmpty)) {
                All[i] = Tile;
                i++;
            }
            All[i] = pickTile;
            return All;
        } else {
            List<tile> result = new ArrayList<>();
            for (tile Tile : contents) {
                if (Tile.getCategory() != EMPTY) {
                    result.add(Tile);
                }

            }
            result.add(getPickTile());
            return result.toArray(new tile[0]);
        }
    }

    /**
     * 副露で生成された面子を取得するメソッドです。
     *
     * @return 副露で生成された面子
     */
    public List<mentsu> getFuuroMentsuList() {
        return fuuroMentsuList;
    }
}
