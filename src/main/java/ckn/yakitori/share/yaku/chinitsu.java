package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tileType;

import java.util.ArrayList;
import java.util.List;

import static ckn.yakitori.share.tile.tileType.FONPAI;
import static ckn.yakitori.share.tile.tileType.SANGEN;
import static ckn.yakitori.share.yaku.yakuInfo.CHINITSU;

public class chinitsu implements yaku {

    private statusGroup StatusGroup;

    public chinitsu(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return CHINITSU;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        //種類保存用のArrayList
        List<tileType> tileTypeList = new ArrayList<>();

        for (mentsu mt : StatusGroup.getMentsuList()) {
            tileTypeList.add(mt.getIdentifierTile().getCategory());
        }
        if (tileTypeList.contains(SANGEN) || tileTypeList.contains(FONPAI)) {
            return false;
        } else {
            boolean flag = true;
            tileType first = tileTypeList.get(0);
            for (int i = 1; i < tileTypeList.size(); i++) {
                if (tileTypeList.get(i) != first) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }

    }
}
