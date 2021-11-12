package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.tile.tile;
import ckn.yakitori.share.tile.tileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.tile.tileType.MANZU;
import static org.junit.jupiter.api.Assertions.*;

class kantsuTest {

    @Test
    @DisplayName("Tileがもってこれるかのテスト")
    void getTile() {
        mentsu Mentsu = new kantsu(false, new tile(MANZU, 2, false), 0);
        assertEquals("tile{2m}", Mentsu.getTile().toString());
    }

    @ParameterizedTest
    @CsvSource({
            "true,0,MANZU,1,false,MANZU,1,false,MANZU,1,false,MANZU,1,false",
            "false,2,SOHZU,8,true,SOHZU,8,true,SOHZU,8,false,SOHZU,9,false",
            "false,2,SOHZU,8,true,SOHZU,8,true,SOHZU,9,false,SOHZU,9,false",
            "false,2,SOHZU,8,true,SOHZU,9,true,SOHZU,9,false,SOHZU,9,false",
            "true,3,SANGEN,3,true,SANGEN,3,true,SANGEN,3,true,SANGEN,3,false",
            "false,1,PINZU,4,false,SOHZU,4,false,MANZU,4,true,MANZU,4,false",
            "false,1,MANZU,5,false,SOHZU,5,false,MANZU,5,true,PINZU,5,false",
            "true,2,SOHZU,9,false,SOHZU,9,true,SOHZU,9,true,SOHZU,9,false",
            "true,1,PINZU,5,false,PINZU,5,true,PINZU,5,false,PINZU,5,false",
            "true,4,PINZU,1,true,PINZU,1,true,PINZU,1,true,PINZU,1,true"
    })
    @DisplayName("槓子が正しくチェックできるかのテスト")
    void check(boolean expected, int expectedred, tileType tileType1, int num1, boolean red1, tileType tileType2, int num2, boolean red2, tileType tileType3, int num3, boolean red3, tileType tileType4, int num4, boolean red4) {
        mentsu Mentsu = new kantsu(false, new tile(tileType1, num1, red1), new tile(tileType2, num2, red2), new tile(tileType3, num3, red3), new tile(tileType4, num4, red4));
        assertEquals(expected, Mentsu.isCheckPass(), "チェック結果がおかしい");
        assertEquals(expectedred, Mentsu.getRedQuantity(), "赤数がおかしい");

    }

    @Test
    @DisplayName("isOpenのテスト")
    void isOpen() {
        mentsu Mentsu = new kantsu(true, new tile(MANZU, 5, false), 0);
        assertTrue(Mentsu.isOpen());
        mentsu Mentsu2 = new kantsu(false, new tile(MANZU, 5, false), 0);
        assertFalse(Mentsu2.isOpen());
    }

    @ParameterizedTest
    @CsvSource({
            "4mr,4m,4m,4m,MANZU,4,false,1",
            "2m,2m,2m,2m,MANZU,2,false,0",
            "8pr,8p,8p,8p,PINZU,8,true,1",
            "8pr,8pr,8p,8p,PINZU,8,true,2",
            "5pr,5pr,5pr,5p,PINZU,5,true,3",
            "6sr,6sr,6s,6s,SOHZU,6,false,2",
            "6mr,6m,6m,6m,MANZU,6,false,1",
            "2sr,2sr,2s,2s,SOHZU,2,false,2",
            "4mr,4mr,4m,4m,MANZU,4,true,2",
            "7pr,7pr,7pr,7pr,PINZU,7,true,4",
    })
    @DisplayName("赤ドラ生成のテスト")
    void getTileFull(String ex1, String ex2, String ex3, String ex4, tileType c, int n, boolean r, int sumr) {
        mentsu Mentsu = new kantsu(false, new tile(c, n, r), sumr);
        tile[] tiles = Mentsu.getTileFull();
        assertEquals(ex1, tiles[0].getFullName(true));
        assertEquals(ex2, tiles[1].getFullName(true));
        assertEquals(ex3, tiles[2].getFullName(true));
        assertEquals(ex4, tiles[3].getFullName(true));
    }

    @Test
    @DisplayName("エラーが出るかのテスト")
    void errorTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new kantsu(false, new tile(MANZU, 5, false), 4));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new kantsu(false, new tile(MANZU, 5, false), -1));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new kantsu(false, new tile(MANZU, 5, true), 5));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new kantsu(false, new tile(MANZU, 5, true), 0));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
    }

    @Test
    void getFu() {
        mentsu Mentsu = new kantsu(true, new tile(MANZU, 5, false), 0);
        assertEquals(8, Mentsu.getFu());
        Mentsu = new kantsu(true, new tile(MANZU, 9, false), 0);
        assertEquals(16, Mentsu.getFu());
        Mentsu = new kantsu(false, new tile(MANZU, 5, false), 0);
        assertEquals(16, Mentsu.getFu());
        Mentsu = new kantsu(false, new tile(MANZU, 9, false), 0);
        assertEquals(32, Mentsu.getFu());
    }

}