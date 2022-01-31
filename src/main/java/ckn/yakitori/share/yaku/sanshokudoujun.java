package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.shuntsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tileType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ckn.yakitori.share.tile.tileType.*;
import static ckn.yakitori.share.yaku.yakuInfo.SANSHOKUDOUJUN;

public class sanshokudoujun implements yaku {

    private statusGroup StatusGroup;

    public sanshokudoujun(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;

    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return SANSHOKUDOUJUN;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        if (StatusGroup.getShuntsuList().size() < 3) {
            return false;
        }

        //数字保存用のArrayList
        List<Integer> tileNumberList = new ArrayList<>();
        //種類保存用のArrayList
        List<tileType> tileTypeList = new ArrayList<>();

        for (shuntsu st : StatusGroup.getShuntsuList()) {
            tileNumberList.add(st.getIdentifierTile().getNumber());
            tileTypeList.add(st.getIdentifierTile().getCategory());
        }

        //順子の数牌判定
        boolean flag = false;
        int count = 0;

        for (int i = 0; i < tileNumberList.size(); i++) {

            for (Integer integer : tileNumberList) {
                if (!Objects.equals(tileNumberList.get(i), integer)) {
                    count += 1;
                }
            }
            if (count < 2) {
                flag = true;
                break;
            }
            count = 0;
        }
        if (!flag) {
            return false;
        }

        if (tileTypeList.contains(MANZU)) {
            if (tileTypeList.contains(PINZU)) {
                return tileTypeList.contains(SOHZU);
            }
        }

        return false;
    }
}
