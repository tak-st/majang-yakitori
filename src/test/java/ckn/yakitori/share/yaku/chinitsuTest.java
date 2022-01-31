package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.CHINITSU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class chinitsuTest {


    @Test
    void getYakuInfo() {
        assertEquals(CHINITSU, new chinitsu(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, 1122335556667m7m",
            "false, 0, 111222333444m7z7z",
            "false, 0, 112233m456789p8m8m",
            "false, 0, 111222333444z1m1m",
            "false, 0, 111m222m5556667z7z"
    })

    @DisplayName("清一色をチェック")
    void isCheckPass(boolean expected, int expectedIndex, String Hand) {

        hand content = new hand(Hand);

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }

        sg.setMentsuList(M.getMentsuList(expectedIndex));
        chinitsu Chinitsu = new chinitsu(sg);
        assertEquals(expected, Chinitsu.isCheckPass(), M.getMentsuList(expectedIndex).toString());

    }

}