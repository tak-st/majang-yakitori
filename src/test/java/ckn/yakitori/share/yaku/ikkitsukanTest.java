package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.IKKITSUKAN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ikkitsukanTest {

    @Test
    void getYakuInfo() {
        assertEquals(IKKITSUKAN, new ikkitsukan(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, 123456789m333p2z2z",
            "false, 0, 123456m123s123p2z2z",
            "false, 0, 111222m333444p2z2z",
            "false, 0, 112233456m2223z3z",
            "false, 0, 445566789m2223z3z",
            "false, 0, 112233789m2223z3z",
            "false, 0, 123445566m789s3z3z"
    })

    @DisplayName("一気通貫をチェック")
    void isCheckPass(boolean expected, int expectedIndex, String Hand) {

        hand content = new hand(Hand);

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }

        sg.setMentsuList(M.getMentsuList(expectedIndex));
        sg.setShuntsuList(M.getShuntsuList(expectedIndex));
        ikkitsukan Ikkitsukan = new ikkitsukan(sg);
        assertEquals(expected, Ikkitsukan.isCheckPass(), M.getMentsuList(expectedIndex).toString());

    }

}