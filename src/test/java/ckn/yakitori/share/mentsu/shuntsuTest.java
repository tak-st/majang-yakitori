package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.tile.tile;
import ckn.yakitori.share.tile.tileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.tile.tileType.MANZU;
import static ckn.yakitori.share.tile.tileType.SANGEN;
import static org.junit.jupiter.api.Assertions.*;

class shuntsuTest {

    @Test
    void getTile() {
        mentsu Mentsu = new shuntsu(false, new tile(MANZU, 2, false), 0);
        assertEquals("tile{2m}", Mentsu.getTile().toString());
    }

    @Test
    void getIdentifierTile() {
    }

    @ParameterizedTest
    @CsvSource({
            "true,0,MANZU,1,false,MANZU,2,false,MANZU,3,false",
            "false,2,SOHZU,8,true,SOHZU,8,true,SOHZU,9,false",
            "false,2,SOHZU,8,true,SOHZU,9,true,SOHZU,9,false",
            "false,3,SANGEN,3,true,SANGEN,3,true,SANGEN,3,true",
            "false,1,PINZU,5,false,SOHZU,3,false,MANZU,4,true",
            "false,1,MANZU,5,false,SOHZU,3,false,MANZU,4,true",
            "true,2,SOHZU,8,false,SOHZU,7,true,SOHZU,9,true",
            "true,1,PINZU,6,false,PINZU,5,true,PINZU,4,false"
    })
    @DisplayName("順子が正しくチェックできるかのテスト")
    void check(boolean expected, int expectedred, tileType tileType1, int num1, boolean red1, tileType tileType2, int num2, boolean red2, tileType tileType3, int num3, boolean red3) {
        mentsu Mentsu = new shuntsu(false, new tile(tileType1, num1, red1), new tile(tileType2, num2, red2), new tile(tileType3, num3, red3));
        assertEquals(expected, Mentsu.isCheckPass(), "チェック結果がおかしい");
        assertEquals(expectedred, Mentsu.getRedQuantity(), "赤数がおかしい");

    }

    @Test
    void isOpen() {
        mentsu Mentsu = new shuntsu(true, new tile(MANZU, 5, false), 0);
        assertTrue(Mentsu.isOpen());
        mentsu Mentsu2 = new shuntsu(false, new tile(MANZU, 5, false), 0);
        assertFalse(Mentsu2.isOpen());
    }

    @ParameterizedTest
    @CsvSource({
            "3m,4m,5mr,MANZU,4,false,1",
            "1m,2m,3m,MANZU,2,false,0",
            "7p,8pr,9p,PINZU,8,true,1",
            "7pr,8pr,9p,PINZU,8,true,2",
            "4pr,5pr,6pr,PINZU,5,true,3",
            "5sr,6s,7sr,SOHZU,6,false,2",
            "5mr,6m,7m,MANZU,6,false,1",
            "1sr,2s,3sr,SOHZU,2,false,2",
            "3m,4mr,5mr,MANZU,4,true,2",
    })
    @DisplayName("順子が正しくチェックできるかのテスト")
    void getTileFull(String ex1, String ex2, String ex3, tileType c, int n, boolean r, int sumr) {
        mentsu Mentsu = new shuntsu(false, new tile(c, n, r), sumr);
        tile[] tiles = Mentsu.getTileFull();
        assertEquals(ex1, tiles[0].getFullName(true));
        assertEquals(ex2, tiles[1].getFullName(true));
        assertEquals(ex3, tiles[2].getFullName(true));
    }

    @Test
    void errorTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new shuntsu(false, new tile(MANZU, 5, false), 3));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new shuntsu(false, new tile(MANZU, 5, false), -1));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new shuntsu(false, new tile(MANZU, 5, true), 4));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new shuntsu(false, new tile(MANZU, 5, true), 0));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new shuntsu(false, new tile(MANZU, 9, true), 2));

        assertEquals("字牌・１・９の牌は識別牌になりません。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new shuntsu(false, new tile(SANGEN, 1, true), 1));

        assertEquals("字牌・１・９の牌は識別牌になりません。", exception.getMessage());
    }

    @Test
    void getFu() {
        mentsu Mentsu = new shuntsu(true, new tile(MANZU, 5, false), 0);
        assertEquals(0, Mentsu.getFu());
    }

}