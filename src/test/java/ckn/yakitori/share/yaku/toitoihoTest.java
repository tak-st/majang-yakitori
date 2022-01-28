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

import static ckn.yakitori.share.yaku.yakuInfo.TOITOIHO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class toitoihoTest {


    @Test
    void getYakuInfo() {
        assertEquals(TOITOIHO, new toitoiho(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, true, false,  TANKI, 1m, 111222m333s444s5p5p",
            "true, 0 , true, false, SYANPON, 1m, 111222m333s444s5p5p",
            "false, 0, false, false, RYANMEN, 1m, 111222m333s444p5p5p",
            "false, 0, false, fasle, SYANPON, 1m, 123456m123456s5p5p",
            "false, 0, false, true, TANKI, 1m, 111222m333444s5p5p",
            "true, 0, true, false, TANKI, 1m, 111222m555666p8p8p",
            "true, 0, false, false, TANKI, 1m, 111222m555777p7s7s"

    })

    @DisplayName("対々和をチェック")
    void isCheckPass(boolean expected, int expectedIndex, boolean expectedIsopen, boolean expectedtsumo, WaitType expectedWaittype, tile kanTile, String Hand) {

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

        sg.setMentsuList(M.getMentsuList(expectedIndex));
        sg.setKantsuList(M.getKantsuList(expectedIndex));
        sg.setKotsuList(M.getKotsuList(expectedIndex));
        sg.setTsumo(expectedtsumo);
        sg.setWaitTypeList(expectedWaittype);
        toitoiho Toitoiho = new toitoiho(sg);
        assertEquals(expected, Toitoiho.isCheckPass(), M.getMentsuList(0).toString());

    }

}