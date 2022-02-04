package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.WaitType;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.SUANKO_TANKI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class suankoTankiTest {


    @Test
    void getYakuInfo() {
        assertEquals(SUANKO_TANKI, new suankoTanki(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 1, false, 1m, TANKI, 111222333444m5z5z",
            "false, 0, false, 1m, RYANMEN, 123m123s123456p5z5z",
            "false, 1, true, 1m, RYANMEN, 111m222333444p5z5z",
            "false, 1, true, 1m, TANKI, 111m222333444p5z5z"
    })

    @DisplayName("三暗刻をチェック")
    void isCheckPass(boolean expected, int expectedIndex, boolean expectedIsopen, tile kanTile, WaitType expectedWaittype, String Hand) {

        hand content = new hand(Hand);

        if (expectedIsopen && kanTile != null) {
            content.doLightKan(kanTile);
            content.sortTile();
        }

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }

        sg.setMentsuList(M, expectedIndex);
        sg.setWaitTypeList(expectedWaittype);
        suankoTanki SuankoTanki = new suankoTanki(sg);
        assertEquals(expected, SuankoTanki.isCheckPass(), M.getMentsuList(expectedIndex).toString());

    }

}