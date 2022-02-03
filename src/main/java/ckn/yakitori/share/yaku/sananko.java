package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;

import static ckn.yakitori.share.yaku.yakuInfo.SANANKO;

public class sananko implements yaku {

    private statusGroup StatusGroup;

    public sananko(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return SANANKO;
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

        int countOpen = 0;

        if (StatusGroup.getKotsuKantsuBoth().size() == 4) {
            countOpen += 1;
        }

        for (mentsu mt : StatusGroup.getKotsuKantsuBoth()) {
            if (mt.isOpen()) {
                countOpen -= 1;
            }
        }

        return countOpen < 0;
    }
}
