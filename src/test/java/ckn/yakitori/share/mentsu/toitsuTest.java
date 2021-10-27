package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.tile.tile;
import ckn.yakitori.share.tile.tileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.tile.tileType.MANZU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class toitsuTest {

    @Test
    void getTile() {
        mentsu Mentsu = new toitsu(new tile(MANZU, 2, false), 0);
        assertEquals("tile{2m}", Mentsu.getTile().toString());
    }

    @Test
    void getIdentifierTile() {
    }

    @ParameterizedTest
    @CsvSource({
            "true,0,1m,1m",
            "true,2,8sr,8sr",
            "false,2,8sr,9sr",
            "false,1,7pr,8s",
            "false,1,6s,4mr",
            "true,2,5zr,5zr",
            "true,1,5pr,5p",
            "false,1,4m,1pr"
    })
    @DisplayName("対子が正しくチェックできるかのテスト")
    void check(boolean expected, int expectedred, String tileString1, String tileString2) {
        mentsu Mentsu = new toitsu(new tile(tileString1), new tile(tileString2));
        assertEquals(expected, Mentsu.isCheckPass(), "チェック結果がおかしい");
        assertEquals(expectedred, Mentsu.getRedQuantity(), "赤数がおかしい");

    }

    @ParameterizedTest
    @CsvSource({
            "4mr,4m,MANZU,4,false,1",
            "2m,2m,MANZU,2,false,0",
            "8pr,8p,PINZU,8,true,1",
            "8pr,8pr,PINZU,8,true,2",
            "5pr,5pr,PINZU,5,true,2",
            "6sr,6s,SOHZU,6,false,1",
            "6mr,6m,MANZU,6,false,1",
            "2sr,2s,SOHZU,2,false,1",
            "4mr,4mr,MANZU,4,true,2",
            "7pr,7pr,PINZU,7,true,2",
    })
    @DisplayName("赤ドラ生成のテスト")
    void getTileFull(String ex1, String ex2, tileType c, int n, boolean r, int sumr) {
        mentsu Mentsu = new toitsu(new tile(c, n, r), sumr);
        tile[] tiles = Mentsu.getTileFull();
        assertEquals(ex1, tiles[0].getFullName(true));
        assertEquals(ex2, tiles[1].getFullName(true));
    }

    @Test
    void errorTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new toitsu(new tile(MANZU, 5, false), 2));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new toitsu(new tile(MANZU, 5, false), -1));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new toitsu(new tile(MANZU, 5, true), 3));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new toitsu(new tile(MANZU, 5, true), 0));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
    }

    @Test
    void getFu() {
        mentsu Mentsu = new toitsu(new tile(MANZU, 5, false), 0);
        assertEquals(0, Mentsu.getFu());
    }

}