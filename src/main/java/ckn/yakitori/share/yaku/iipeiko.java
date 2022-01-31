package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.shuntsu;
import ckn.yakitori.share.score.statusGroup;

import java.util.ArrayList;
import java.util.List;

import static ckn.yakitori.share.yaku.yakuInfo.IIPEIKO;

public class iipeiko implements yaku {

    private final ArrayList<shuntsu> shuntsuList;
    private statusGroup StatusGroup;

    public iipeiko(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
        this.shuntsuList = StatusGroup.getShuntsuList();
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return IIPEIKO;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        List<String> tile = new ArrayList<>();

        for (shuntsu st : shuntsuList) {

            if (StatusGroup.isOpen()) {
                break;
            }
            if (tile.contains(st.getIdentifierTile().getFullName())) {
                return true;
            }

            tile.add(st.getIdentifierTile().getFullName());

        }
        return false;
    }

}
