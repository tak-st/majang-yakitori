package ckn.yakitori.server.discard;


import ckn.yakitori.share.tile.tile;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 捨て牌に起こせるアクションがある場合の処理を行います。
 *
 * @author mizu
 * @version 1.0
 */
public class discardArea {


    public ArrayList<discardTile> discardList = new ArrayList<>();

    /**
     * @param disTile 捨て牌の情報を追加します。
     */
    public void addDiscardTile(discardTile disTile) {
        discardList.add(disTile);
    }

    /**
     * 渡されたtile.getFullName(false)と同じものがarrayListの中にあるか確認
     *
     * @param Tile 捨て牌の情報
     * @return 一致した牌情報
     */
    public ArrayList<Integer> check(tile Tile) {
        ArrayList<Integer> match = new ArrayList<>();
        for (int i = 0; i < getDiscardList().size(); i++) {
            if (Objects.equals(getDiscardList().get(i).getTile().getFullName(), Tile.getFullName(false))) {
                match.add(i);
            }
        }
        return match;
    }

    /**
     * 捨て牌リストの中身が全てヤオチュウハイで、鳴かれていなければtrueを返し流し満貫です。
     *
     * @return 流し満貫かどうかを返します。
     */
    public boolean Nagashicheck() {

        for (int i = 0; i < getDiscardList().size(); i++) {
            if (!getDiscardList().get(i).getTile().getYaochu()) {
                return false;
            }
            if (getDiscardList().get(i).isStealed()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<discardTile> getDiscardList() {
        return discardList;
    }

    public discardTile getDiscardList(int num) {
        return discardList.get(num);
    }
}