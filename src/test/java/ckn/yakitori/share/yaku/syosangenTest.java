package ckn.yakitori.share.yaku;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.mentsu.mentsuPartition;
import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.SYOSANGEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class syosangenTest {


    @Test
    void getYakuInfo() {
        assertEquals(SYOSANGEN, new syosangen(new statusGroup()).getYakuInfo());
    }


    @ParameterizedTest
    @CsvSource({
            "true, 0, 123456m555z666z7z7z",
            "false, 0, 123456789m123s2z2z",
            "false, 0, 111m222s333m666z7z7z"
    })

    @DisplayName("小三元をチェック")
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
        syosangen Syosangen = new syosangen(sg);
        assertEquals(expected, Syosangen.isCheckPass(), M.getMentsuList(expectedIndex).toString());
    }
}