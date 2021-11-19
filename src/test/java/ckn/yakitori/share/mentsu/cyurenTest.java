package ckn.yakitori.share.mentsu;

import ckn.yakitori.share.hand;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.tile.tileType.MANZU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class cyurenTest {
    @ParameterizedTest
    @CsvSource({
            "true,0,1112345678999m2m",
            "true,3,111mr2345678999m3m",
            "true,5,11123mr45678999m7m",
            "false,0,1112345678999m9m",
            "false,0,1112345678999m1m",
            "false,0,1112223456999m8m",
            "false,0,1112223456999m8s",
            "false,0,1112233456789m9m",
            "true,14,1112345678999mr6mr",
    })
    @DisplayName("九蓮宝燈が正しくチェックできるかのテスト")
    void check(boolean expected, int expectedred, String handString1) {
        mentsu Mentsu = new cyuren(new hand(handString1));
        assertEquals(expected, Mentsu.isCheckPass(), "チェック結果がおかしい");
        assertEquals(expectedred, Mentsu.getRedQuantity(), "赤数がおかしい");

    }

    @ParameterizedTest
    @CsvSource({
            "1m,1m,1m,2m,2m,3m,4m,5m,6m,7m,8m,9m,9m,9m,2m,0",
            "1m,1m,1m,2m,3m,3m,4m,5mr,6m,7m,8m,9m,9m,9m,3m,1",
            "1m,1m,1m,2m,3m,4mr,4m,5mr,6m,7m,8m,9m,9m,9m,4m,2",
            "1m,1m,1m,2m,3m,4mr,5mr,5m,6mr,7m,8m,9m,9m,9m,5m,3",
            "1m,1m,1m,2m,3mr,4mr,5mr,6mr,6m,7m,8m,9m,9m,9m,6m,4",
            "1m,1m,1m,2m,3mr,4mr,5mr,6mr,7mr,7m,8m,9m,9m,9m,7m,5",
            "1m,1m,1m,2mr,3mr,4mr,5mr,6mr,7mr,8m,8m,9m,9m,9m,8m,6",
            "1m,1m,1m,2mr,2m,3mr,4mr,5mr,6mr,7mr,8mr,9m,9m,9m,2m,7",
            "1mr,1m,1m,2mr,2m,3mr,4mr,5mr,6mr,7mr,8mr,9m,9m,9m,2m,8",
            "1mr,1m,1m,2mr,2m,3mr,4mr,5mr,6mr,7mr,8mr,9mr,9m,9m,2m,9",
            "1mr,1mr,1m,2mr,2m,3mr,4mr,5mr,6mr,7mr,8mr,9mr,9m,9m,2m,10",
            "1mr,1mr,1m,2mr,2m,3mr,4mr,5mr,6mr,7mr,8mr,9mr,9mr,9m,2m,11",
            "1mr,1mr,1mr,2mr,2m,3mr,4mr,5mr,6mr,7mr,8mr,9mr,9mr,9m,2m,12",
            "1mr,1mr,1mr,2mr,2m,3mr,4mr,5mr,6mr,7mr,8mr,9mr,9mr,9mr,2m,13",
            "1mr,1mr,1mr,2mr,2mr,3mr,4mr,5mr,6mr,7mr,8mr,9mr,9mr,9mr,2mr,14",
    })
    @DisplayName("牌一つから正しく判定できるかのテスト")
    void getTileFull(String ex1, String ex2, String ex3, String ex4, String ex5, String ex6, String ex7, String ex8, String ex9, String ex10, String ex11, String ex12, String ex13, String ex14, String tileString1, int Red) {
        mentsu Mentsu = new cyuren(new tile(tileString1), Red);
        tile[] tiles = Mentsu.getTileFull();
        assertEquals(ex1, tiles[0].getFullName(true));
        assertEquals(ex2, tiles[1].getFullName(true));
        assertEquals(ex3, tiles[2].getFullName(true));
        assertEquals(ex4, tiles[3].getFullName(true));
        assertEquals(ex5, tiles[4].getFullName(true));
        assertEquals(ex6, tiles[5].getFullName(true));
        assertEquals(ex7, tiles[6].getFullName(true));
        assertEquals(ex8, tiles[7].getFullName(true));
        assertEquals(ex9, tiles[8].getFullName(true));
        assertEquals(ex10, tiles[9].getFullName(true));
        assertEquals(ex11, tiles[10].getFullName(true));
        assertEquals(ex12, tiles[11].getFullName(true));
        assertEquals(ex13, tiles[12].getFullName(true));
        assertEquals(ex14, tiles[13].getFullName(true));
    }

    @Test
    @DisplayName("エラーが出るかのテスト")
    void errorTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new cyuren(new tile("5m"), 14));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new cyuren(new tile("5m"), -1));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new cyuren(new tile("5mr"), 15));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new cyuren(new tile("5mr"), 0));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new cyuren(new tile("1mr"), 2));

        assertEquals("１、９、字牌は識別牌になりません。", exception.getMessage());
    }

    @Test
    @DisplayName("符数のテスト")
    void getFu() {
        mentsu Mentsu = new cyuren(new tile(MANZU, 2, false), 0);
        assertEquals(0, Mentsu.getFu());
    }

    @Test
    @DisplayName("toStringのテスト")
    void toStringTest() {
        mentsu Mentsu = new cyuren(new tile(MANZU, 2, true), 14);
        assertEquals("九蓮{2m*2+12牌 r14}", Mentsu.toString());
    }
}