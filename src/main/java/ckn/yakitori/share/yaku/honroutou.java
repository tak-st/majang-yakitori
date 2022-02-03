package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tileType;

import java.util.ArrayList;
import java.util.List;

import static ckn.yakitori.share.tile.tileType.FONPAI;
import static ckn.yakitori.share.tile.tileType.SANGEN;
import static ckn.yakitori.share.yaku.yakuInfo.HONROUTOU;

public class honroutou implements yaku {

    private statusGroup StatusGroup;

    public honroutou(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;

    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return HONROUTOU;
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

        //数字保存用のArrayList
        List<Integer> tileNumberList = new ArrayList<>();
        //種類保存用のArrayList
        List<tileType> tileTypeList = new ArrayList<>();

        for (mentsu mt : StatusGroup.getKotsuKantsuBoth()) {
            tileNumberList.add(mt.getIdentifierTile().getNumber());
            tileTypeList.add(mt.getIdentifierTile().getCategory());
        }
        tileTypeList.add(StatusGroup.getToitsuList().get(0).getIdentifierTile().getCategory());
        tileNumberList.add(StatusGroup.getToitsuList().get(0).getIdentifierTile().getNumber());

        for (int i = 0; i < tileTypeList.size(); i++) {
            if (!tileTypeList.get(i).equals(SANGEN) && !tileTypeList.get(i).equals(FONPAI)) {
                if (tileNumberList.get(i) != 1 && tileNumberList.get(i) != 9) {
                    return false;
                }
            }
        }
        return true;
    }
}
