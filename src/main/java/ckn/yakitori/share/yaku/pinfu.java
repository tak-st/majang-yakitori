package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.WaitType;
import ckn.yakitori.share.mentsu.toitsu;
import ckn.yakitori.share.score.statusGroup;

import static ckn.yakitori.share.tile.tileType.SANGEN;
import static ckn.yakitori.share.yaku.yakuInfo.PINFU;

public class pinfu implements yaku {

    private statusGroup StatusGroup;

    public pinfu(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return PINFU;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        if (StatusGroup.isOpen()) {
            return false;
        }

        if (StatusGroup.getWaitTypeList() != WaitType.RYANMEN) {
            return false;
        }

        if (StatusGroup.getKotsuList().size() != 0) {
            return false;
        }
        for (toitsu tt : StatusGroup.getToitsuList()) {
            if (tt.getIdentifierTile().getCategory() == SANGEN) {
                return false;
            }
            if (tt.getIdentifierTile().getNumber() == StatusGroup.getJikaze()) {
                return false;
            }
            if (tt.getIdentifierTile().getNumber() == StatusGroup.getBakaze()) {
                return false;
            }
        }
        return true;
    }
}
