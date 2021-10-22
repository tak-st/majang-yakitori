package ckn.yakitori.server;

import ckn.yakitori.share.tile.tile;
import ckn.yakitori.share.tile.tileType;

import java.util.ArrayList;
import java.util.Collections;

import static ckn.yakitori.server.mountainType.SANMA;
import static ckn.yakitori.share.tile.tileType.*;

/**
 * 山牌を作成し、情報を保持するクラスです。
 *
 * <p></p>
 *
 * @author mizu
 * @author keita
 * @version 1.1
 */
public class mountain {
    /**
     * 山牌
     */
    private final ArrayList<tile> tileList = new ArrayList<>();
    /**
     * 引いた牌の数
     */
    private int pick_number = 0;
    /**
     * 山の生成時の牌の数
     */
    private int contents_length = 0;


    /**
     * 渡されたtypeに従い山を生成するコンストラクタです。
     *
     * @param type 山の種類を指定する引数
     * @throws IllegalArgumentException 実装されていないtypeが渡されたときの例外
     */
    public mountain(mountainType type) {
        tileType tileCategory;
        switch (type) {
            case YONMA, SANMA -> {
            }
            default -> throw new IllegalArgumentException("実装されていないタイプです。");
        }
        // a = 1萬子、2索子、3筒子、4字牌
        for (int a = 1; a <= 4; a++) {
            switch (a) {

                case 1 -> tileCategory = MANZU;
                case 2 -> tileCategory = SOHZU;
                case 3 -> tileCategory = PINZU;
                default -> tileCategory = ZIPAI;

            }
            // 字牌の場合 7牌 字牌以外なら 9牌
            if (tileCategory == ZIPAI) {
                for (int c = 1; c <= 7; c++) {
                    // b = 何枚目か
                    for (int b = 1; b <= 4; b++) {
                        tileList.add(new tile(tileCategory, c, false));
                        this.contents_length++;
                    }
                }

            } else {
                for (int c = 1; c <= 9; c++) {
                    // 三麻で萬子で2~8の場合、何もしない
                    if (!(type == SANMA && tileCategory == MANZU && c >= 2 && c <= 8)) {
                        // b = 何枚目か
                        for (int b = 1; b <= 4; b++) {
                            // 五萬・五索・五筒である、4枚目の場合 赤ドラにする
                            if (c == 5 && b == 4) {

                                tileList.add(new tile(tileCategory, c, true));

                            } else {

                                tileList.add(new tile(tileCategory, c, false));

                            }

                            this.contents_length++;
                        }
                    }
                }
            }
        }


        //山牌を表示（確認用）
        //System.out.println(tileList);

        //山牌をシャッフル
        Collections.shuffle(tileList);

        //ランダムにした山牌を表示
        //System.out.println(tileList);
    }

    /**
     * 山牌の残り牌を返すメソッドです。
     *
     * @return 残り牌の数を示すint型
     */
    public int getRemaingTile() {
        return this.contents_length - this.pick_number;
    }

    /**
     * 山の情報を文字列として表示するメソッドです。
     *
     * @return 山の情報を表示するString型
     */
    @Override
    public String toString() {
        return "mountain{" +
                "tileList=" + tileList +
                ", pick_number=" + pick_number +
                ", contents_length=" + contents_length +
                '}';
    }

    /**
     * ツモるメソッドです。
     *
     * @return ツモった牌を示すtile型
     */
    public tile pickTile() {

        return tileList.get(pick_number++);
    }
}
