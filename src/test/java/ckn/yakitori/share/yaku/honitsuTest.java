package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.HONITSU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class honitsuTest {


    @Test
    void getYakuInfo() {
        assertEquals(HONITSU, new honitsu(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 123456789m111z2z2z",
            "false, 123456789m123s2z2z",
            "true, 1112223334445z5z"
    })

    @DisplayName("混一色をチェック")
    void isCheckPass(boolean expected, String Hand) {

        hand content = new hand(Hand);
        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }
        sg.setMentsuList(M.getMentsuList(0));
        honitsu Honitsu = new honitsu(sg);
        assertEquals(expected, Honitsu.isCheckPass(), M.getMentsuList(0).toString());

    }

}