package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tileType;

import java.util.ArrayList;
import java.util.List;

import static ckn.yakitori.share.tile.tileType.SANGEN;
import static ckn.yakitori.share.yaku.yakuInfo.SYOSANGEN;

public class syosangen implements yaku {


    private statusGroup StatusGroup;

    public syosangen(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return SYOSANGEN;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        if (StatusGroup.getKotsuKantsuBoth().size() < 2) {
            return false;
        }

        //種類保存用のArrayList
        List<tileType> tileTypeList = new ArrayList<>();

        for (mentsu mt : StatusGroup.getKotsuKantsuBoth()) {
            tileTypeList.add(mt.getIdentifierTile().getCategory());
        }
        tileTypeList.add(StatusGroup.getToitsuList().get(0).getIdentifierTile().getCategory());

        for (tileType tt : tileTypeList) {
            if (!tt.equals(SANGEN)) {
                return false;
            }
        }
        return true;

    }
}
