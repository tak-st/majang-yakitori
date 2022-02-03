package ckn.yakitori.share.yaku;

import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.Test;

import static ckn.yakitori.share.yaku.yakuInfo.CYANKAN;
import static org.junit.jupiter.api.Assertions.*;

class cyankanTest {

    @Test
    void getYakuInfo() {
        assertEquals(CYANKAN, new cyankan(new statusGroup()).getYakuInfo());
    }

    @Test
    void isCheckPass() {
        statusGroup sg = new statusGroup();
        sg.setIscyankan(true);
        cyankan Cyankan = new cyankan(sg);
        assertTrue(Cyankan.isCheckPass());
        sg.setIscyankan(false);
        Cyankan = new cyankan(sg);
        assertFalse(Cyankan.isCheckPass());
    }
}