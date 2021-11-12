package ckn.yakitori.share.yaku;

import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.Test;

import static ckn.yakitori.share.yaku.yakuInfo.RIICHI;
import static org.junit.jupiter.api.Assertions.*;

class riichiTest {

    @Test
    void getYakuInfo() {
        assertEquals(RIICHI, new riichi(new statusGroup()).getYakuInfo());
    }

    @Test
    void isCheckPass() {
        statusGroup sg = new statusGroup();
        sg.setRiichi(true);
        riichi Riichi = new riichi(sg);
        assertTrue(Riichi.isCheckPass());
        sg.setRiichi(false);
        Riichi = new riichi(sg);
        assertFalse(Riichi.isCheckPass());
    }
}