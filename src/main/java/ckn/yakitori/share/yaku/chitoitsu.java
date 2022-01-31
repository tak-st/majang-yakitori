package ckn.yakitori.share.yaku;

import ckn.yakitori.share.score.statusGroup;

import static ckn.yakitori.share.yaku.yakuInfo.CHITOITSU;

public class chitoitsu implements yaku {

    private statusGroup StatusGroup;

    public chitoitsu(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;

    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return CHITOITSU;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        return StatusGroup.getToitsuList().size() >= 7;
    }
}
