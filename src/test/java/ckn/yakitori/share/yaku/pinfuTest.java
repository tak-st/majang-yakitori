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

import static ckn.yakitori.share.yaku.yakuInfo.PINFU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class pinfuTest {


    @Test
    void getYakuInfo() {
        assertEquals(PINFU, new pinfu(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, false, RYANMEN, 1z, 1z, , 45678m12399p456s9m",
            "false, true, RYANMEN, 1z, 1z, 9p, 12345m123999p22s6m",
            "false, false, RYANMEN, 1z, 1z, , 123456m123456s6z6z",
            "false, false, RYANMEN, 1z, 2z, , 123456m123456s1z1z",
            "false, false, RYANMEN, 2z, 1z, , 123456m123456s1z1z",
            "false, false, RYANMEN, 1z, 1z, , 123456m123s333z2s2s",
            "true, false, RYANMEN, 1z, 1z, , 123456m123456s2z2z",
            "false, false, TANKI, 1z, 1z, , 123456m123456s2z2z",
    })

    @DisplayName("平和をチェック")
    void isCheckPass(boolean expected, boolean expectedIsopen, WaitType expectedWaittype, tile expectedJikaze, tile expectedBakaze, tile kanTile, String Hand) {

        hand content = new hand(Hand);

        if (expectedIsopen) {
            content.doLightKan(kanTile);
            content.sortTile();

        }

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }

        sg.setMentsuList(M, 0);
        sg.setWaitTypeList(expectedWaittype);
        sg.setJikaze(expectedJikaze.getNumber());
        sg.setBakaze(expectedBakaze.getNumber());
        pinfu Pinfu = new pinfu(sg);
        assertEquals(expected, Pinfu.isCheckPass(), M.getMentsuList(0).toString());

    }

}