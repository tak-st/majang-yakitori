package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.IIPEIKO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class iipeikoTest {

    @Test
    void getYakuInfo() {
        assertEquals(IIPEIKO, new iipeiko(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, false, 1m, 123123m345s222z2p2p",
            "false, false, 1m, 111m222333s444p1p1p",
            "false, true, 1m, 111m223344s555p1p1p"
    })

    @DisplayName("一盃口をチェック")
    void isCheckPass(boolean expected, boolean expectedIsopen, tile Tile, String Hand) {

        hand content = new hand(Hand);

        if (expectedIsopen) {
            content.doLightKan(Tile);
            content.sortTile();
        }

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }
        sg.setMentsuList(M.getMentsuList(0));
        sg.setShuntsuList(M.getShuntsuList(0));
        iipeiko Iipeiko = new iipeiko(sg);
        assertEquals(expected, Iipeiko.isCheckPass(), M.getShuntsuList(0).toString());

    }

}