package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;

import static ckn.yakitori.share.tile.tileType.FONPAI;
import static ckn.yakitori.share.tile.tileType.SANGEN;
import static ckn.yakitori.share.yaku.yakuInfo.CYANTA;

public class cyanta implements yaku {

    private statusGroup StatusGroup;

    public cyanta(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return CYANTA;
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
            if (mt.getIdentifierTile().getCategory() != SANGEN && mt.getIdentifierTile().getCategory() != FONPAI) {
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
