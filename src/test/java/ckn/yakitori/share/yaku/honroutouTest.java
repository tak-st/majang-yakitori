package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.HONROUTOU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class honroutouTest {


    @Test
    void getYakuInfo() {
        assertEquals(HONROUTOU, new honroutou(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, 111m111s111p444z5z5z",
            "true, 0, 111999m111999s9p9p",
            "false, 0, 123456m111s222p5z5z",
            "false, 0, 111222m111222p5z5z",
            "false, 0, 123m123345s567p5z5z",
            "false, 0, 123123m123p5556z6z",
            "false, 0, 112233s123p5556z6z",
            "false, 0, 112233m123s5556z6z"
    })

    @DisplayName("混老頭をチェック")
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
        honroutou Honroutou = new honroutou(sg);
        assertEquals(expected, Honroutou.isCheckPass(), M.getMentsuList(expectedIndex).toString());

    }

}