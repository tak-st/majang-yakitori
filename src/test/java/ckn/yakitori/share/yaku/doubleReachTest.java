package ckn.yakitori.share.yaku;

import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.DOUBLE_REACH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class doubleReachTest {

    @Test
    void getYakuInfo() {
        assertEquals(DOUBLE_REACH, new doubleReach(new statusGroup()).getYakuInfo());
    }

    @ParameterizedTest
    @CsvSource({
            "true, true",
            "false, false"
    })

    @DisplayName("ダブル立直をチェック")
    void isCheckPass(boolean expected, boolean expectedDoubleReach) {
        statusGroup sg = new statusGroup();
        sg.setDoubleReach(expectedDoubleReach);
        doubleReach DoubleReach = new doubleReach(sg);
        assertEquals(expected, DoubleReach.isCheckPass());
    }
}