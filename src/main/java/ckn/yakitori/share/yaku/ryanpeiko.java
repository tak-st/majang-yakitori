package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.shuntsu;
import ckn.yakitori.share.score.statusGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ckn.yakitori.share.yaku.yakuInfo.RYANPEIKO;

public class ryanpeiko implements yaku {

    private final ArrayList<shuntsu> shuntsuList;
    private statusGroup StatusGroup;

    public ryanpeiko(statusGroup StatusGroup) {
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

        return RYANPEIKO;
    }

    /**
     * 役判定の結果を返します。
     *
     * @return 役が成立しているかどうか
     */
    @Override
    public boolean isCheckPass() {

        List<String> identifierTileList = new ArrayList<>();
        List<String> identifierTileSabList = new ArrayList<>();

        if (StatusGroup.isOpen() || shuntsuList.size() < 4) {
            return false;
        }

        for (shuntsu st : shuntsuList) {
            identifierTileList.add(st.getIdentifierTile().getFullName());
            identifierTileSabList.add(st.getIdentifierTile().getFullName());
        }

        identifierTileSabList.removeAll(Collections.singleton(identifierTileList.get(0)));

        if (identifierTileSabList.size() % 4 == 0) {
            return true;
        } else if (identifierTileSabList.size() % 4 == 2) {
            return identifierTileSabList.get(0).equals(identifierTileSabList.get(1));
        }
        return false;
    }
}