package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.SANSYOKUDOUKO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class sansyokudoukoTest {


    @Test
    void getYakuInfo() {
        assertEquals(SANSYOKUDOUKO, new sansyokudouko(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, 111m111s111p444z5z5z",
            "true, 0, 111999m111999s9p9p",
            "true, 0, 111222m333s123p5z5z",
            "false, 0, 111222m123s666z3p3p",
            "false, 0, 111m111s333z555z9p9p",
            "false, 0, 123456m111s222p5z5z",
            "false, 0, 123m123345s567p5z5z",
            "false, 0, 123123m123p5556z6z",
            "false, 0, 112233s123p5556z6z",
            "false, 0, 112233m123s5556z6z"
    })

    @DisplayName("三色同刻をチェック")
    void isCheckPass(boolean expected, int expectedIndex, String Hand) {

        hand content = new hand(Hand);

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }

        sg.setMentsuList(M.getMentsuList(expectedIndex));
        sg.setKotsuList(M.getKotsuList(expectedIndex));
        sg.setToitsuList(M.getToitsuList(expectedIndex));
        sansyokudouko Sansyokudouko = new sansyokudouko(sg);
        assertEquals(expected, Sansyokudouko.isCheckPass(), M.getMentsuList(expectedIndex).toString());

    }

}