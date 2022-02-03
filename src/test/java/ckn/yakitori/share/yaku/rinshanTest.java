package ckn.yakitori.share.yaku;

import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.yaku.yakuInfo.RINSHAN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class rinshanTest {

    @Test
    void getYakuInfo() {
        assertEquals(RINSHAN, new rinshan(new statusGroup()).getYakuInfo());
    }

    @ParameterizedTest
    @CsvSource({
            "true, true",
            "false, false"
    })

    @DisplayName("嶺上開花をチェック")
    void isCheckPass(boolean expected, boolean expectedIsRinshan) {
        statusGroup sg = new statusGroup();
        sg.setRinshan(expectedIsRinshan);
        rinshan Rinshan = new rinshan(sg);
        assertEquals(expected, Rinshan.isCheckPass());
    }
}