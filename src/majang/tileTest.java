package majang;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class tileTest {

    @Test
    @DisplayName("不正牌チェックのテスト")
    void createClass() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new tile('a', 8, false), "8a");
        assertEquals("牌の種類が不正です。", exception.getMessage());
        Throwable exception2 = assertThrows(
                IllegalArgumentException.class,
                () -> new tile('s', 10, false), "10s");
        assertEquals("牌の数字が不正です。", exception2.getMessage());
        Throwable exception3 = assertThrows(
                IllegalArgumentException.class,
                () -> new tile('m', -3, false), "-3m");
        assertEquals("牌の数字が不正です。", exception3.getMessage());
        Throwable exception4 = assertThrows(
                IllegalArgumentException.class,
                () -> new tile('z', 8, false), "8z");
        assertEquals("牌の数字が不正です。", exception4.getMessage());
    }

    @Test
    @DisplayName("FullName表示のテスト")
    void getFullName() {
        assertEquals("2s", new tile('s', 2, false).getFullName(), "2s");
        assertEquals("4m", new tile('m', 4, false).getFullName(), "4m");
        assertEquals("8p", new tile('p', 8, true).getFullName(), "8p(r)");
        assertEquals("5z", new tile('z', 5, false).getFullName(), "5z");
    }

    @Test
    @DisplayName("getSortIDのテスト")
    void getSortID() throws Exception {
        assertEquals(11, new tile('m', 1, false).getSortID(), "1m");
        assertEquals(170, new tile('p', 7, true).getSortID(), "7p(r)");
        assertEquals(291, new tile('s', 9, false).getSortID(), "9s");
        assertEquals(371, new tile('z', 7, false).getSortID(), "7z");
    }

}