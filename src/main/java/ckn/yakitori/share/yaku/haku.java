package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.kantsu;
import ckn.yakitori.share.mentsu.kotsu;
import ckn.yakitori.share.score.statusGroup;

import java.util.ArrayList;
import java.util.Objects;

import static ckn.yakitori.share.yaku.yakuInfo.HAKU;

public class haku implements yaku {

    private statusGroup StatusGroup;
    private ArrayList<kotsu> kotsuList = new ArrayList<>();
    private ArrayList<kantsu> kantsuList = new ArrayList<>();

    public haku(statusGroup StatusGroup) {
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
        return HAKU;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        for (kotsu kot : kotsuList) {
            if (Objects.equals(kot.getIdentifierTile().getFullName(), "5z")) {
                return true;
            }
        }
        for (kantsu kat : kantsuList) {
            if (Objects.equals(kat.getIdentifierTile().getFullName(), "5z")) {
                return true;
            }
        }
        return false;
    }
}
