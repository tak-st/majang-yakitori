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

class kokushiTest {
    @ParameterizedTest
    @CsvSource({
            "true,0,1m9m1s9s1p9p1z2z3z4z5z6z7z7z",
            "false,0,1m9m1s9s1p9p1z2z3z4z6z6z7z7z",
            "false,0,1m9m1s9s1p9p1z2z4z4z6z6z7z7z",
            "true,0,1m9m1s9s1p9p1z2z3z4z5z6z7z1m",
            "false,0,1m9m1s5s1p9p1z2z3z4z5z6z7z1m",
            "false,0,1m9m1s9s1p9p1z2z3z4z5z6z7z5m",
            "true,0,1m9m1s9s1p9p9p1z2z3z4z5z6z7z",
            "true,0,1m9m1s9s1p9p1z2z3z4z5z6z7z9p",
            "true,0,1m9m1s9s1p9p1z2z3z4z5z6z7z9s",
            "true,0,1m9m1s9s1p9p1z2z3z4z5z6z7z4z",
            "false,0,1m9m1s9s1p9p1z2z3z4z5z6z7z5m",
            "false,0,1m9m1s9s1p9p1z2z3z4z5z6z4m5m",
            "true,0,1m9m1s9s1p9p1z2z3z4z5z6z6z7z",
            "true,14,1mr9mr1sr9sr1pr9pr123456zr7zr7zr",
    })
    @DisplayName("国士無双が正しくチェックできるかのテスト")
    void check(boolean expected, int expectedred, String handString1) {
        mentsu Mentsu = new kokushi(new hand(handString1));
        assertEquals(expected, Mentsu.isCheckPass(), "チェック結果がおかしい");
        assertEquals(expectedred, Mentsu.getRedQuantity(), "赤数がおかしい");

    }

    @ParameterizedTest
    @CsvSource({
            "1m,1m,9m,1p,9p,1s,9s,1z,2z,3z,4z,5z,6z,7z,1m,0",
            "1mr,9m,9m,1p,9p,1s,9s,1z,2z,3z,4z,5z,6z,7z,9m,1",
            "1mr,9mr,1p,1p,9p,1s,9s,1z,2z,3z,4z,5z,6z,7z,1p,2",
            "1mr,9mr,1pr,9p,9p,1s,9s,1z,2z,3z,4z,5z,6z,7z,9p,3",
            "1mr,9mr,1pr,9pr,1s,1s,9s,1z,2z,3z,4z,5z,6z,7z,1s,4",
            "1mr,9mr,1pr,9pr,1sr,9s,9s,1z,2z,3z,4z,5z,6z,7z,9s,5",
            "1mr,9mr,1pr,9pr,1sr,9sr,1z,1z,2z,3z,4z,5z,6z,7z,1z,6",
            "1mr,9mr,1pr,9pr,1sr,9sr,1zr,2z,2z,3z,4z,5z,6z,7z,2z,7",
            "1mr,9mr,1pr,9pr,1sr,9sr,1zr,2zr,3z,3z,4z,5z,6z,7z,3z,8",
            "1mr,9mr,1pr,9pr,1sr,9sr,1zr,2zr,3zr,4z,4z,5z,6z,7z,4z,9",
            "1mr,9mr,1pr,9pr,1sr,9sr,1zr,2zr,3zr,4zr,5z,5z,6z,7z,5z,10",
            "1mr,9mr,1pr,9pr,1sr,9sr,1zr,2zr,3zr,4zr,5zr,6z,6z,7z,6z,11",
            "1mr,9mr,1pr,9pr,1sr,9sr,1zr,2zr,3zr,4zr,5zr,6zr,7z,7z,7z,12",
            "1mr,9mr,1pr,9pr,1sr,9sr,1zr,2zr,3zr,4zr,5zr,6zr,7zr,7z,7z,13",
            "1mr,9mr,1pr,9pr,1sr,9sr,1zr,2zr,3zr,4zr,5zr,6zr,7zr,7zr,7zr,14",
    })
    @DisplayName("牌一つから正しく判定できるかのテスト")
    void getTileFull(String ex1, String ex2, String ex3, String ex4, String ex5, String ex6, String ex7, String ex8, String ex9, String ex10, String ex11, String ex12, String ex13, String ex14, String tileString1, int Red) {
        mentsu Mentsu = new kokushi(new tile(tileString1), Red);
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
    void errorTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new kokushi(new tile("5m"), 14));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new kokushi(new tile("5m"), -1));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new kokushi(new tile("5mr"), 15));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new kokushi(new tile("5mr"), 0));

        assertEquals("赤ドラの数がおかしいです。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new kokushi(new tile("2mr"), 2));

        assertEquals("２～８の牌は識別牌になりません。", exception.getMessage());
    }

    @Test
    void getFu() {
        mentsu Mentsu = new kokushi(new tile(MANZU, 1, false), 0);
        assertEquals(0, Mentsu.getFu());
    }
}