package ckn.yakitori.share.yaku;

import ckn.yakitori.share.score.statusGroup;
import org.junit.jupiter.api.Test;

import static ckn.yakitori.share.yaku.yakuInfo.IPPATSU;
import static org.junit.jupiter.api.Assertions.*;

class ippatsuTest {

    @Test
    void getYakuInfo() {
        assertEquals(IPPATSU, new ippatsu(new statusGroup()).getYakuInfo());
    }

    @Test
    void isCheckPass() {
        statusGroup sg = new statusGroup();
        sg.setIppatsu(true);
        ippatsu Ippatsu = new ippatsu(sg);
        assertTrue(Ippatsu.isCheckPass());
        sg.setIppatsu(false);
        Ippatsu = new ippatsu(sg);
        assertFalse(Ippatsu.isCheckPass());
    }
}