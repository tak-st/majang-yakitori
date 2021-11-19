package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.kantsu;
import ckn.yakitori.share.mentsu.kotsu;
import ckn.yakitori.share.score.statusGroup;

import java.util.ArrayList;

import static ckn.yakitori.share.tile.tileType.FONPAI;
import static ckn.yakitori.share.yaku.yakuInfo.BAKAZE;

public class bakaze implements yaku {

    private final int bakaze;
    private final ArrayList<kotsu> kotsuList;
    private final ArrayList<kantsu> kantsuList;

    public bakaze(statusGroup StatusGroup) {
        this.bakaze = StatusGroup.getBakaze();
        this.kotsuList = StatusGroup.getKotsuList();
        this.kantsuList = StatusGroup.getKantsuList();
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {
        return BAKAZE;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {
        for (kotsu kot : kotsuList) {
            if (kot.getIdentifierTile().getCategory() == FONPAI) {
                if (kot.getIdentifierTile().getNumber() == bakaze) {
                    return true;
                }
            }
        }
        for (kantsu kat : kantsuList) {
            if (kat.getIdentifierTile().getCategory() == FONPAI) {
                if (kat.getIdentifierTile().getNumber() == bakaze) {
                    return true;
                }
            }
        }
        return false;
    }
}
