package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.CHITOITSU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class chitoitsuTest {


    @Test
    void getYakuInfo() {
        assertEquals(CHITOITSU, new chitoitsu(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, 1144m2255p3355z7m7m",
            "false, 0, 111222333444m5z5z"
    })

    @DisplayName("七対子をチェック")
    void isCheckPass(boolean expected, int expectdindex, String Hand) {

        hand content = new hand(Hand);

        statusGroup sg = new statusGroup();
        mentsuPartition M = new mentsuPartition(content);
        if (!M.isCanWin()) {
            fail("上がれない");
        }

        sg.setMentsuList(M.getMentsuList(expectdindex));
        sg.setToitsuList(M.getToitsuList(expectdindex));
        chitoitsu Chitoitsu = new chitoitsu(sg);
        assertEquals(expected, Chitoitsu.isCheckPass(), M.getMentsuList(expectdindex).toString());

    }

}