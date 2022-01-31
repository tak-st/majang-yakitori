package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.SANSHOKUDOUJUN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class sanshokudoujunTest {


    @Test
    void getYakuInfo() {
        assertEquals(SANSHOKUDOUJUN, new sanshokudoujun(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, 123m123s123p444z5z5z",
            "true, 0, 123456m123s123p5z5z",
            "false, 0, 123456m111s222p5z5z",
            "false, 0, 111222m111222p5z5z",
            "false, 0, 123m123345s567p5z5z",
            "false, 0, 123123m123p5556z6z",
            "false, 0, 112233s123p5556z6z",
            "false, 0, 112233m123s5556z6z"
    })

    @DisplayName("三色同順をチェック")
    void isCheckPass(boolean expected, int expectedindex, String Hand) {

        hand content = new hand(Hand);

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }

        sg.setMentsuList(M.getMentsuList(expectedindex));
        sg.setShuntsuList(M.getShuntsuList(expectedindex));
        sanshokudoujun Sanshokudoujun = new sanshokudoujun(sg);
        assertEquals(expected, Sanshokudoujun.isCheckPass(), M.getMentsuList(expectedindex).toString());

    }

}