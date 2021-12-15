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

import static ckn.yakitori.share.yaku.yakuInfo.TANYAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class tanyaoTest {


    @Test
    void getYakuInfo() {
        assertEquals(TANYAO, new tanyao(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "false, true, false, 1m , 1112222333444m5m",
            "true, true, false, 1m , 2333344455668m8m",
            "false, true, false, 1m , 1112223334m111z4m",
            "false, true, false, 1m , 2223334445559m9m",
            "false, false, false, 1m , 1112222333444m5m",
            "false, true, true, 1m , 1112333444555m2mr",
            "false, false, true, 2p, 2223334445556p6p",
            "true, true, true, 2p, 2223334445556p6p",
            "true, true, true, 3s, 3334445556667s7s"
    })

    @DisplayName("断么をチェック")
    void isCheckPass(boolean expected, boolean expectedKuitan, boolean expectedIsopen, tile Tile, String Hand) {

        hand content = new hand(Hand);

        if (expectedIsopen) {
            content.doLightKan(Tile);
            content.sortTile();

        }

        ArrayList<mentsu> mentsuList = new ArrayList<>();
        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }
        sg.setMentsuList(M.getMentsuList(0));
        sg.setCanKuitan(expectedKuitan);
        tanyao Tanyao = new tanyao(sg);
        assertEquals(expected, Tanyao.isCheckPass(), M.getMentsuList(0).toString());

    }

}