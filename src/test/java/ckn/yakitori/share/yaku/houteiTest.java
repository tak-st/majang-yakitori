package ckn.yakitori.share.yaku;

import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.HOUTEI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class houteiTest {

    @Test
    void getYakuInfo() {
        assertEquals(HOUTEI, new houtei(new statusGroup()).getYakuInfo());
    }

    @ParameterizedTest
    @CsvSource({
            "true, true, false",
            "false, false, false",
            "false, true, true"
    })

    @DisplayName("河底撈魚をチェック")
    void isCheckPass(boolean expected, boolean expectedIsHaitei, boolean expectedIsTsumo) {
        statusGroup sg = new statusGroup();
        sg.setHaitei(expectedIsHaitei);
        sg.setTsumo(expectedIsTsumo);
        houtei Houtei = new houtei(sg);
        assertEquals(expected, Houtei.isCheckPass());
    }
}