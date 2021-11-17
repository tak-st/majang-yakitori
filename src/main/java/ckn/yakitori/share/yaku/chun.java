package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.kantsu;
import ckn.yakitori.share.mentsu.kotsu;
import ckn.yakitori.share.score.statusGroup;

import java.util.ArrayList;
import java.util.Objects;

import static ckn.yakitori.share.yaku.yakuInfo.CHUN;

public class chun implements yaku {

    private statusGroup StatusGroup;
    private ArrayList<kotsu> kotsuList = new ArrayList<>();
    private ArrayList<kantsu> kantsuList = new ArrayList<>();

    public chun(statusGroup StatusGroup) {
        this.StatusGroup = StatusGroup;
        this.kotsuList = StatusGroup.getKotsuList();
        this.kantsuList = StatusGroup.getKantsuList();
    }

    /**
     * 役が持つ飜数などのデータが取得できるEnumを返します。
     *
     * @return それぞれの役のEnum
     */
    @Override
    public yakuInfo getYakuInfo() {
        return CHUN;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        for (kotsu kot : kotsuList) {
            if (Objects.equals(kot.getIdentifierTile().getFullName(), "7z")) {
                return true;
            }
        }
        for (kantsu kat : kantsuList) {
            if (Objects.equals(kat.getIdentifierTile().getFullName(), "7z")) {
                return true;
            }
        }
        return false;
    }
}