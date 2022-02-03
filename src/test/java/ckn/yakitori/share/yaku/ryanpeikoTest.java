package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.RYANPEIKO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ryanpeikoTest {

    @Test
    void getYakuInfo() {
        assertEquals(RYANPEIKO, new ryanpeiko(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, false, 1m, 111122223333m2p2p",
            "false, 0, false, 1m, 123456789m123p4z4z",
            "false, 0, true, 1m, 111m444555s666p7z7z",
            "false, 0, false, 1m, 111222333m555s7z7z",
            "false, 0, false, 1m, 1122334567899m9m",
            "false, 0, false, 1m, 112233m456s789p7z7z",
            "false, 0, false, 1m, 111222333m123s9p9p",
            "true, 2, false, 1s, 112233m112233s9p9p"

    })

    @DisplayName("一盃口をチェック")
    void isCheckPass(boolean expected, int expectedIndex, boolean expectedKan, tile Tile, String Hand) {

        hand content = new hand(Hand);

        if (expectedKan) {
            content.doLightKan(Tile);
            content.sortTile();
        }

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }
        sg.setMentsuList(M.getMentsuList(expectedIndex));
        sg.setShuntsuList(M.getShuntsuList(expectedIndex));

        ryanpeiko Ryanpeiko = new ryanpeiko(sg);
        assertEquals(expected, Ryanpeiko.isCheckPass(), M.getMentsuList(expectedIndex).toString());

    }

}