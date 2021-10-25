package ckn.yakitori.share;

import ckn.yakitori.server.mountainEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static ckn.yakitori.server.mountainType.YONMA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class handTest {


    @ParameterizedTest
    @CsvSource({
            "hand{1m2m3m4m5m6m7m8m9m1s2s3s4s / 4s},123456789m1234s4s",
            "hand{1mr2mr3mr4mr5mr6mr7mr8mr9mr1s2s3s4s / 4s},123456789mr1234s4s",
            "hand{1s2s3s4p5pr6p7m8m9m1z2z3z4z / 5z},123s4p5pr6p789m123z4z5z6z7z",
            "hand{5m6m6m6m7m7m7m8m8m8m9m9m9m / 5mr},5666777888999m5mr",


    })
    @DisplayName("文字列を使用して初期化するテスト")
    void testString(String expected, String string) {
        hand Hand = new hand(string);
        assertEquals(expected, Hand.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "認識できた牌が12牌以下でした。：1牌,7z",
            "認識できた牌が12牌以下でした。：12牌,123456789m123s",
            "8文字目に、認識できない文字が含まれています。：0,3456z7890b12345m7z",
            "牌の数字が不正です。,123456789z1234z5z",
            "上がり牌が認識できませんでした。上がり牌は文字列の最後に追加する必要があります。,33344455566677m",


    })
    @DisplayName("例外を吐くかどうかのテスト")
    void testStringEx(String expected, String string) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new hand(string), string);

        assertEquals(expected, exception.getMessage());
    }

    @Test
    void testToString() {
        mountain Mountain = new mountainEntity(YONMA);
        hand Hand = new hand(Mountain);
        Hand.doPickTile(Mountain);
        Hand.sortTile();
        System.out.println(Hand);
    }
}