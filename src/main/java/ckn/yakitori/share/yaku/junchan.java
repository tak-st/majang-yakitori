package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;

import static ckn.yakitori.share.tile.tileType.FONPAI;
import static ckn.yakitori.share.tile.tileType.SANGEN;
import static ckn.yakitori.share.yaku.yakuInfo.JUNCHAN;

public class junchan implements yaku {

    private statusGroup StatusGroup;

    public junchan(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return JUNCHAN;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        LOOP_I:
        for (mentsu mt : StatusGroup.getMentsuList()) {
            if (mt.getIdentifierTile().getCategory() == SANGEN || mt.getIdentifierTile().getCategory() == FONPAI) {
                return false;
            } else {
                tile[] tile = mt.getTileFull();
                for (tile tl : tile) {
                    if (tl.getNumber() == 1 || tl.getNumber() == 9) {
                        continue LOOP_I;
                    }
                }
                return false;
            }
        }
        return true;
    }
}
