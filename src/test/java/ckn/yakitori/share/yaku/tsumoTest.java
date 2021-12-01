package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsu;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static ckn.yakitori.share.yaku.yakuInfo.TSUMO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class tsumoTest {

    @Test
    void getYakuInfo() {
        assertEquals(TSUMO, new tsumo(new statusGroup()).getYakuInfo());
    }

    @ParameterizedTest
    @CsvSource({
            "true, false, true, 1m, 1112222333444m5m",
            "false, true, false, 1m, 1112222333444m5m",
            "false, false, false,  , 1112222333444m5m"
    })

    @DisplayName("門前自摸をチェック")
    void isCheckPass(boolean expected, boolean expectedIsopen, boolean expectedTsumo, tile Tile, String Hand) {

        hand content = new hand(Hand);

        if (expectedIsopen) {
            content.doPon(Tile);
            content.sortTile();
            content.setContents(content.getAll()[0], 11);
            content.setContents(new tile("e"), 1);
        }

        ArrayList<mentsu> mentsuList = new ArrayList<>();
        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        for (int i = 0; i <= M.getMentsuList().size() - 1; i++) {
            for (mentsu mt : M.getMentsuList(i)) {
                mentsuList.add(mt);
            }
        }

        sg.setTsumo(expectedTsumo);
        sg.setIsopen(mentsuList);
        tsumo Tsumo = new tsumo(sg);
        assertEquals(expected, Tsumo.isCheckPass());
    }
}