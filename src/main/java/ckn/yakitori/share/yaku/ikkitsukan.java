package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.shuntsu;
import ckn.yakitori.share.score.statusGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ckn.yakitori.share.yaku.yakuInfo.IKKITSUKAN;

public class ikkitsukan implements yaku {

    private statusGroup StatusGroup;

    public ikkitsukan(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;

    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {

        return IKKITSUKAN;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        //一気通貫用のデータ
        String[][] ikkitsukanData = {
                {"2m", "5m", "8m"},
                {"2s", "5s", "8s"},
                {"2p", "5p", "8p"}
        };
        List<String> ikkitsukanList;

        if (StatusGroup.getShuntsuList().size() < 3) {
            return false;
        }

        //牌保存用のリスト
        List<String> tileFullList = new ArrayList<>();

        for (shuntsu st : StatusGroup.getShuntsuList()) {
            tileFullList.add(st.getIdentifierTile().getFullName());
        }

        for (String[] strings : ikkitsukanData) {
            ikkitsukanList = Arrays.asList(strings);
            if (tileFullList.containsAll(ikkitsukanList)) {
                return true;
            }
        }
        return false;

    }
}
