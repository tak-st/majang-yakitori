package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;

import java.util.ArrayList;

import static ckn.yakitori.share.tile.tileType.*;
import static ckn.yakitori.share.yaku.yakuInfo.TANYAO;

public class tanyao implements yaku {


    private final ArrayList<mentsu> mentsuList;
    private statusGroup StatusGroup;


    public tanyao(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
        this.mentsuList = StatusGroup.getMentsuList();
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return TANYAO;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        LOOP_I:
        for (mentsu mt : mentsuList) {

            if (!StatusGroup.isCanKuitan() && StatusGroup.isOpen()) {
                break;
            }
            tile[] Tile = mt.getTileFull();
            for (tile tl : Tile) {

                if (tl.getCategory() != MANZU && tl.getCategory() != PINZU && tl.getCategory() != SOHZU) {
                    break LOOP_I;
                }
                if (tl.getNumber() < 2 || tl.getNumber() > 8) {
                    break LOOP_I;
                }
            }
            if (mt == mentsuList.get(mentsuList.size() - 1)) {
                return true;
            }
        }
        return false;
    }
}
