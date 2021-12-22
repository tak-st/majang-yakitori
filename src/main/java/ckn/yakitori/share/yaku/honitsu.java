package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tileType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static ckn.yakitori.share.tile.tileType.FONPAI;
import static ckn.yakitori.share.tile.tileType.SANGEN;
import static ckn.yakitori.share.yaku.yakuInfo.HONITSU;

public class honitsu implements yaku {

    private statusGroup StatusGroup;

    public honitsu(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return HONITSU;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        //面子のリスト
        ArrayList<mentsu> mentsuList = StatusGroup.getMentsuList();
        //面子カテゴリのリスト
        ArrayList<tileType> mentsuCategoryList = new ArrayList<>();


        for (mentsu m : mentsuList) {
            mentsuCategoryList.add(m.getIdentifierTile().getCategory());
        }
        List<tileType> set = new ArrayList<>();
        Collections.addAll(set, SANGEN, FONPAI);
        mentsuCategoryList.removeAll(set);

        mentsuCategoryList = new ArrayList<>(new HashSet<>(mentsuCategoryList));
        return mentsuCategoryList.size() <= 1;
    }
}
