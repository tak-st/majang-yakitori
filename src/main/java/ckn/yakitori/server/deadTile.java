package ckn.yakitori.server;

import ckn.yakitori.share.mountain;
import ckn.yakitori.share.tile.tile;


/**
 * 王牌の情報を保持するクラスです。
 */
public class deadTile {

    /**
     * 嶺上牌
     */
    tile[] rinsyan = new tile[4];
    /**
     * ドラ牌
     */
    tile[] dora = new tile[5];
    /**
     * 裏ドラ
     */
    tile[] hiddendora = new tile[5];


    /**
     * 嶺上牌カウント
     */
    int rinsyan_count = 0;
    /**
     * ドラ牌カウント
     */
    int dora_count = 0;
    /**
     * 裏ドラ牌カウント
     */
    int hiddendora_count = 0;


    /**
     * 山牌から王牌を取り出すコンストラクタです。
     *
     * @param Mountain 山牌を指定する引数
     */
    public deadTile(mountain Mountain) {
        for (int i = 0; i <= 3; i++) {
            rinsyan[i] = Mountain.pickTile();
            dora[i] = Mountain.pickTile();
            hiddendora[i] = Mountain.pickTile();
        }
        dora[4] = Mountain.pickTile();
        hiddendora[4] = Mountain.pickTile();
    }

    /**
     * 嶺上牌を返すメソッドです。
     *
     * @return 嶺上牌を返すtile型
     */
    public tile getRinsyan() {
        return rinsyan[rinsyan_count++];
    }

    /**
     * ドラ牌を返すメソッドです。
     *
     * @return ドラ牌を返すtile型
     */
    public tile getDora() {
        return dora[dora_count++];
    }

    /**
     * 裏ドラ牌を返すメソッドです。
     *
     * @return 裏ドラ牌を返すtile型
     */
    public tile getHiddenDora() {
        return hiddendora[hiddendora_count++];
    }
}
