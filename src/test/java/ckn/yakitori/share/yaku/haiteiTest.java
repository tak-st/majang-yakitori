package ckn.yakitori.share.yaku;

import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.HAITEI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class haiteiTest {

    @Test
    void getYakuInfo() {
        assertEquals(HAITEI, new haitei(new statusGroup()).getYakuInfo());
    }

    @ParameterizedTest
    @CsvSource({
            "true, true, true",
            "false, false, false",
            "false, true, false"
    })

    @DisplayName("海底摸月をチェック")
    void isCheckPass(boolean expected, boolean expectedIsHaitei, boolean expectedIsTsumo) {
        statusGroup sg = new statusGroup();
        sg.setHaitei(expectedIsHaitei);
        sg.setTsumo(expectedIsTsumo);
        haitei Haitei = new haitei(sg);
        assertEquals(expected, Haitei.isCheckPass());
    }
}