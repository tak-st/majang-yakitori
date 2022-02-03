package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.SANANKO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class sanankoTest {


    @Test
    void getYakuInfo() {
        assertEquals(SANANKO, new sananko(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, false, 1m, , , 111m222s333444p5z5z",
            "true, 0, true, , , 6m, 111567m222s333p5z5z",
            "true, 0, true, 1m, , , 111m222s333444p5z5z",
            "false, 0, false, 1m, , , 123456m123s5666z5z",
            "false, 0, true, 1m, 2m , , 111222m222s345p5z5z"
    })

    @DisplayName("三暗刻をチェック")
    void isCheckPass(boolean expected, int expectedIndex, boolean expectedIsopen, tile kanTile, tile kantile2, tile chiTile, String Hand) {

        hand content = new hand(Hand);

        if (expectedIsopen && kanTile != null) {
            content.doLightKan(kanTile);
            content.sortTile();
        }

        if (expectedIsopen && kantile2 != null) {
            content.doLightKan(kantile2);
            content.sortTile();
        }

        //チーできるように面子分解に渡すデータを15牌->14牌に調整
        if (expectedIsopen && chiTile != null) {
            content.doChii(chiTile, 3, 5);
            content.sortTile();
            content.setContents(new tile("e"), 3);
        }

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        System.out.println(M.getMentsuList(expectedIndex).toString());
        if (!M.isCanWin()) {
            fail("上がれない");
        }

        sg.setMentsuList(M, expectedIndex);
        sananko Sananko = new sananko(sg);
        assertEquals(expected, Sananko.isCheckPass(), M.getMentsuList(expectedIndex).toString());

    }

}