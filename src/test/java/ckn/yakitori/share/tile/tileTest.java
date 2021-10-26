package ckn.yakitori.share.tile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.share.tile.tileType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class tileTest {

    @ParameterizedTest
    @CsvSource({
            "FONPAI,ZIPAI,1,false,1",
            "FONPAI,ZIPAI,4,false,4",
            "SANGEN,ZIPAI,5,false,1",
            "SANGEN,ZIPAI,7,false,3"

    })
    @DisplayName("字牌入力から風牌や三元牌へ変換")
    void getCategory(tileType expected, tileType category, int number, boolean isRed, int expectedNum) {
        assertEquals(expected, new tile(category, number, isRed).getCategory(), "Category");
        assertEquals(expectedNum, new tile(category, number, isRed).getNumber(), "Number");
    }

    @ParameterizedTest
    @CsvSource({
            "2s,s,2,false",
            "4m,m,4,false",
            "8p,p,8,true",
            "1z,z,1,false",
            "5z,z,5,false"

    })
    @DisplayName("FullName表示のテスト")
    void getFullName(String expected, char category, int number, boolean isRed) {
        assertEquals(expected, new tile(category, number, isRed).getFullName());
    }

    @ParameterizedTest
    @CsvSource({
            "1z,FONPAI,1,false",
            "4z,FONPAI,4,false",
            "5z,SANGEN,1,true",
            "7z,SANGEN,3,false"

    })
    @DisplayName("FullName表示のテスト：風牌と三元牌")
    void getFullNameTileType(String expected, tileType category, int number, boolean isRed) {
        assertEquals(expected, new tile(category, number, isRed).getFullName(false));
    }

    @ParameterizedTest
    @CsvSource({
            "11,m,1,false",
            "150,p,5,true",
            "291,s,9,false",
            "371,z,7,false"

    })
    @DisplayName("getSortIDのテスト")
    void getSortID(int expected, char category, int number, boolean isRed) {
        assertEquals(expected, new tile(category, number, isRed).getSortID());
    }

    @ParameterizedTest
    @CsvSource({
            "true,MANZU,1,false",
            "true,SOHZU,9,false",
            "true,SANGEN,3,true",
            "true,FONPAI,2,true",
            "false,PINZU,6,false",
            "false,SOHZU,4,false",
            "true,ZIPAI,6,false"
    })
    @DisplayName("getYaochuのテスト")
    void getYaochu(boolean expected, tileType category, int number, boolean isRed) {
        assertEquals(expected, new tile(category, number, isRed).getYaochu());
    }

    @Nested
    @DisplayName("不正牌チェックのテスト")
    class missTile {
        @Test
        @DisplayName("牌の種類が'a'")
        void missTileA() {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile('a', 8, false), "8a");

            assertEquals("牌の種類が不正です。", exception.getMessage());
        }

        @Test
        @DisplayName("牌の数字が'10'")
        void missTile10() {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile('s', 10, false), "10s");

            assertEquals("牌の数字が不正です。", exception.getMessage());
        }

        @Test
        @DisplayName("牌の数字が'0'")
        void missTile0() {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile('z', 0, false), "0z");

            assertEquals("牌の数字が不正です。", exception.getMessage());
        }

        @Test
        @DisplayName("牌の数字が'-3'")
        void missTileMinus3() {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile('m', -3, false), "-3m");

            assertEquals("牌の数字が不正です。", exception.getMessage());
        }

        @Test
        @DisplayName("字牌なのに牌の数字が'8'")
        void missTileZ8() {
            Throwable exception4 = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile('z', 8, false), "8z");

            assertEquals("牌の数字が不正です。", exception4.getMessage());
        }

    }

    @Nested
    @DisplayName("tileType型を使用した初期化での不正牌チェックのテスト")
    class missTileType {
        @Test
        @DisplayName("牌の数字が'10'")
        void missTile10() {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile(SOHZU, 10, false), "10s");

            assertEquals("牌の数字が不正です。", exception.getMessage());
        }

        @Test
        @DisplayName("牌の数字が'0'")
        void missTile0() {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile(ZIPAI, 0, false), "0z");

            assertEquals("牌の数字が不正です。", exception.getMessage());
        }

        @Test
        @DisplayName("牌の数字が'-3'")
        void missTileMinus3() {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile(PINZU, -3, false), "-3m");

            assertEquals("牌の数字が不正です。", exception.getMessage());
        }

        @Test
        @DisplayName("字牌なのに牌の数字が'8'")
        void missTileZ8() {
            Throwable exception4 = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile(ZIPAI, 8, false), "8z");

            assertEquals("牌の数字が不正です。", exception4.getMessage());
        }

        @Test
        @DisplayName("風牌なのに牌の数字が'5'")
        void missTileK5() {
            Throwable exception4 = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile(FONPAI, 5, true), "5k");

            assertEquals("牌の数字が不正です。", exception4.getMessage());
        }

        @Test
        @DisplayName("三元牌なのに牌の数字が'4'")
        void missTileG4() {
            Throwable exception4 = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile(SANGEN, 4, false), "4g");

            assertEquals("牌の数字が不正です。", exception4.getMessage());
        }

    }

    @Test
    @DisplayName("toString()を使用した際、文字列で中身を示す")
    void toStringTest() {
        assertEquals("tile{5mr}", new tile(MANZU, 5, true).toString());
        assertEquals("tile{6z}", new tile(SANGEN, 2, false).toString());
    }

    @Nested
    class StringTest {

        @ParameterizedTest
        @CsvSource({
                "3m,3m",
                "4mr,4mr",
                "6m,m6",
                "9mr,mr9",
                "2sr,rs2",
                "5sr,r5s",
                "8p,8p",
                "5z,z5",
        })
        void StringCTest(String expected, String string) {
            assertEquals(expected, new tile(string).getFullName(true));
        }

        @ParameterizedTest
        @CsvSource({
                "数字が2つ以上存在します。,13m",
                "種類を示す文字が2つ以上存在します。,1mp",
                "1文字目に、認識できない文字が含まれています。：a,am1",
                "牌を設定するための情報が不足しています。,3",
                "牌を設定するための情報が不足しています。,z",
                "牌の数字が不正です。,9z",
        })
        void StringErrorTest(String expected, String string) {
            Throwable exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new tile(string));

            assertEquals(expected, exception.getMessage());
        }
    }


}