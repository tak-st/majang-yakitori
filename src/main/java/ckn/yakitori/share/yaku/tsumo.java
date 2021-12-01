package ckn.yakitori.share.yaku;

import ckn.yakitori.share.score.statusGroup;

import static ckn.yakitori.share.yaku.yakuInfo.TSUMO;

public class tsumo implements yaku {


    private statusGroup StatusGroup;

    public tsumo(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return TSUMO;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {
        if (!StatusGroup.isOpen()) {
            return StatusGroup.isTsumo();
        }
        return false;
    }
}
