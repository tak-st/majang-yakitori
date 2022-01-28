package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.WaitType;
import ckn.yakitori.share.score.statusGroup;

import static ckn.yakitori.share.yaku.yakuInfo.TOITOIHO;

public class toitoiho implements yaku {

    private statusGroup StatusGroup;

    public toitoiho(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;

    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return TOITOIHO;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {


        if (StatusGroup.getWaitTypeList() != WaitType.SYANPON && StatusGroup.getWaitTypeList() != WaitType.TANKI) {
            return false;
        }


        if (StatusGroup.getKotsuKantsuBoth().size() == 4) {
            //四暗刻になるのでfalse
            return StatusGroup.isOpen() || !StatusGroup.isTsumo();
        }
        return false;
    }
}
