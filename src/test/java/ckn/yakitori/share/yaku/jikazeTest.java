package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.kantsu;
import ckn.yakitori.share.mentsu.kotsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static ckn.yakitori.share.yaku.yakuInfo.JIKAZE;
import static org.junit.jupiter.api.Assertions.*;

class jikazeTest {


    @Test
    void getYakuInfo() {
        assertEquals(JIKAZE, new jikaze(new statusGroup()).getYakuInfo());
    }


    @Test
    void isCheckPass() {
        //刻子テスト-True
        kotsu hand1 = new kotsu(false, new tile("1z"), 0);
        ArrayList<kotsu> kotsuList = new ArrayList<>();
        kotsuList.add(hand1);
        statusGroup sg = new statusGroup();
        sg.setKotsuList(kotsuList);
        jikaze Jikaze = new jikaze(sg);
        assertTrue(Jikaze.isCheckPass());
        //刻子テスト-False
        hand1 = new kotsu(false, new tile("6z"), 0);
        kotsuList.clear();
        kotsuList.add(hand1);
        Jikaze = new jikaze(sg);
        sg.setKotsuList(kotsuList);
        Jikaze = new jikaze(sg);
        assertFalse(Jikaze.isCheckPass());
        //槓子テスト-True
        kantsu hand2 = new kantsu(false, new tile("1z"), 0);
        ArrayList<kantsu> kantsuList = new ArrayList<>();
        kantsuList.add(hand2);
        sg.setKantsuList(kantsuList);
        Jikaze = new jikaze(sg);
        assertTrue(Jikaze.isCheckPass());
        //槓子テスト-False
        hand2 = new kantsu(false, new tile("6z"), 0);
        kantsuList.clear();
        kantsuList.add(hand2);
        Jikaze = new jikaze(sg);
        sg.setKantsuList(kantsuList);
        Jikaze = new jikaze(sg);
        assertFalse(Jikaze.isCheckPass());
    }

}