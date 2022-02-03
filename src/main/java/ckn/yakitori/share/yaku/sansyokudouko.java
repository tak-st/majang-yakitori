package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;

import static ckn.yakitori.share.tile.tileType.FONPAI;
import static ckn.yakitori.share.tile.tileType.SANGEN;
import static ckn.yakitori.share.yaku.yakuInfo.SANSYOKUDOUKO;

public class sansyokudouko implements yaku {

    private statusGroup StatusGroup;

    public sansyokudouko(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;

    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return SANSYOKUDOUKO;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        if (StatusGroup.getKotsuKantsuBoth().size() < 3) {
            return false;
        }

        //数牌以外の牌が刻子と槓子にいくつ含まれているかのカウンタ
        int count = 0;

        for (mentsu mt : StatusGroup.getKotsuKantsuBoth()) {
            if (mt.getIdentifierTile().getCategory().equals(SANGEN) || mt.getIdentifierTile().getCategory().equals(FONPAI)) {
                count += 1;
            }
        }
        if (StatusGroup.getKotsuKantsuBoth().size() == 4) {
            return count < 2;
        } else {
            return count < 1;
        }
    }
}
