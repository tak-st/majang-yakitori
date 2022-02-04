package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.WaitType;
import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;

import static ckn.yakitori.share.yaku.yakuInfo.SUANKO_TANKI;

public class suankoTanki implements yaku {

    private statusGroup StatusGroup;

    public suankoTanki(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return SUANKO_TANKI;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        if (StatusGroup.getKotsuKantsuBoth().size() < 4) {
            return false;
        }

        if (StatusGroup.getWaitTypeList() != WaitType.TANKI) {
            return false;
        }

        for (mentsu mt : StatusGroup.getKotsuKantsuBoth()) {
            if (mt.isOpen()) {
                return false;
            }
        }
        return true;
    }
}
