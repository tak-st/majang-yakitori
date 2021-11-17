package ckn.yakitori.share.yaku;

import ckn.yakitori.share.mentsu.kantsu;
import ckn.yakitori.share.mentsu.kotsu;
import ckn.yakitori.share.score.statusGroup;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static ckn.yakitori.share.yaku.yakuInfo.BAKAZE;
import static org.junit.jupiter.api.Assertions.*;

class bakazeTest {


    @Test
    void getYakuInfo() {
        assertEquals(BAKAZE, new bakaze(new statusGroup()).getYakuInfo());
    }


    @Test
    void isCheckPass() {
        //刻子テスト-True
        kotsu hand1 = new kotsu(false, new tile("1z"), 0);
        ArrayList<kotsu> kotsuList = new ArrayList<>();
        kotsuList.add(hand1);
        statusGroup sg = new statusGroup();
        sg.setBakaze(1);
        sg.setKotsuList(kotsuList);
        bakaze Bakaze = new bakaze(sg);
        assertTrue(Bakaze.isCheckPass());
        //刻子テスト-False
        hand1 = new kotsu(false, new tile("6z"), 0);
        kotsuList.clear();
        kotsuList.add(hand1);
        sg.setBakaze(5);
        sg.setKotsuList(kotsuList);
        Bakaze = new bakaze(sg);
        assertFalse(Bakaze.isCheckPass());
        //槓子テスト-True
        kantsu hand2 = new kantsu(false, new tile("4z"), 0);
        ArrayList<kantsu> kantsuList = new ArrayList<>();
        kantsuList.add(hand2);
        sg.setBakaze(4);
        sg.setKantsuList(kantsuList);
        Bakaze = new bakaze(sg);
        assertTrue(Bakaze.isCheckPass());
        //槓子テスト-False
        hand2 = new kantsu(false, new tile("5z"), 0);
        kantsuList.clear();
        kantsuList.add(hand2);
        sg.setBakaze(2);
        sg.setKantsuList(kantsuList);
        Bakaze = new bakaze(sg);
        assertFalse(Bakaze.isCheckPass());
    }

}