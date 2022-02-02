package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.CYANTA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class cyantaTest {


    @Test
    void getYakuInfo() {
        assertEquals(CYANTA, new cyanta(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, 123789m123789s5z5z",
            "true, 0, 123789m111z6667z7z",
            "false, 0, 123789m456s5556z6z",
            "false, 0, 123456789m123s6z6z",
            "false, 0, 1144m2255s3366p6z6z",
            "false, 0, 111m222s333444p3z3z",
            "false, 0, 111m234567s555z3z3z"
    })

    @DisplayName("混全帯ヤオ九をチェック")
    void isCheckPass(boolean expected, int expectedIndex, String Hand) {

        hand content = new hand(Hand);

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }

        sg.setMentsuList(M.getMentsuList(0));
        cyanta Cyanta = new cyanta(sg);
        assertEquals(expected, Cyanta.isCheckPass(), M.getMentsuList(0).toString());

    }

}