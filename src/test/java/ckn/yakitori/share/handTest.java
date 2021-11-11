package ckn.yakitori.share;

import ckn.yakitori.server.mountainEntity;
import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static ckn.yakitori.server.mountainType.SEQUENCE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class handTest {


    @ParameterizedTest
    @CsvSource({
            "手牌{1m2m3m4m5m6m7m8m9m1s2s3s4s / 4s},123456789m1234s4s",
            "手牌{1mr2mr3mr4mr5mr6mr7mr8mr9mr1s2s3s4s / 4s},123456789mr1234s4s",
            "手牌{1s2s3s4p5pr6p7m8m9m1z2z3z4z / 5z},123s4p5pr6p789m123z4z5z6z7z",
            "手牌{5m6m6m6m7m7m7m8m8m8m9m9m9m / 5mr},5666777888999m5mr",
            "手牌{1mr9mr1sr9sr1pr9pr1zr2zr3zr4zr5zr6zr7zr / 7zr},1mr9mr1sr9sr1pr9pr1zr2zr3zr4zr5zr6zr7zr7zr",
            "手牌{1mr9mr1sr9sr1pr9pr1zr2zr3zr4zr5zr6zr7zr / 7z},1mr9mr1sr9sr1pr9pr1zr2zr3zr4zr5zr6zr7zr7z",

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
    @DisplayName("toStringのテスト")
    void testToString() {
        mountain Mountain = new mountainEntity(SEQUENCE);
        for (int i = 0; i <= 30; i++) {
            Mountain.pickTile();
        }
        hand Hand = new hand(Mountain);
        Hand.doPickTile(Mountain);
        Hand.sortTile();
        assertEquals("手牌{8m9m9m9m9m1s1s1s1s2s2s2s2s / 3s}", Hand.toString());
    }

    @Test
    void ponTest() {
        hand Hand = new hand("1112223334445m5m");
        Hand.doPon(new tile("1mr"));
        assertEquals("手牌{1m2m2m2m3m3m3m4m4m4m5m / 5m < [刻{1m*3 r1 <op>}] >}", Hand.toString());
    }

    @Test
    void LightkanTest() {
        hand Hand = new hand("1112223334445m5m");
        Hand.doLightKan(new tile("1mr"));
        assertEquals("手牌{2m2m2m3m3m3m4m4m4m5m / 5m < [槓{1m*4 r1 <L>}] >}", Hand.toString());
        Hand = new hand("1111223334445m5m");
        Hand.doLightKan(new tile("1mr"));
        assertEquals("手牌{1m2m2m3m3m3m4m4m4m5m / 5m < [槓{1m*4 r1 <L>}] >}", Hand.toString());
    }

    @Test
    void DarkkanTest() {
        hand Hand = new hand("1111223334445m5m");
        Hand.doDarkKan(3);
        assertEquals("手牌{2m2m3m3m3m4m4m4m5m / 5m < [槓{1m*4}] >}", Hand.toString());
        Hand = new hand("1111123334445m5m");
        Hand.doDarkKan(3);
        assertEquals("手牌{1m2m3m3m3m4m4m4m5m / 5m < [槓{1m*4}] >}", Hand.toString());
    }

    @Test
    void AddkanTest() {
        hand Hand = new hand("1122233344455m1mr");
        Hand.doPon(new tile("1mr"));
        assertEquals("手牌{2m2m2m3m3m3m4m4m4m5m5m / 1mr < [刻{1m*3 r1 <op>}] >}", Hand.toString());
        Hand.doAddKan(11);
        assertEquals("手牌{2m2m2m3m3m3m4m4m4m5m5m /  < [槓{1m*4 r2 <+>}] >}", Hand.toString());
    }

    @Test
    void ChiiTest() {
        hand Hand = new hand("12mr23334455667m1mr");
        Hand.doChii(new tile("3m"), 1, 6);
        System.out.println(Hand);
        assertEquals("手牌{1mr2m3m3m3m4m5m5m6m6m7m / 1mr < [順{2m3m4m r1 <op>}] >}", Hand.toString());
    }

    @Test
    void hadakaTankiTest() {
        hand Hand = new hand("1123555666677m1mr");
        Hand.doChii(new tile("4m"), 2, 3);
        Hand.doPon(new tile("1m"));
        Hand.doLightKan(new tile("5m"));
        Hand.doDarkKan(0);
        Hand.doAddKan(2);
        assertEquals("[tile{7m}, tile{7m}, tile{}, tile{}, tile{}, tile{}, tile{}, tile{}, tile{}, tile{}, tile{}, tile{}, tile{}, tile{}]", Arrays.toString(Hand.getAll(true)));
        assertEquals("[順{2m3m4m <op>}, 槓{5m*4 <L>}, 槓{6m*4}, 槓{1m*4 r1 <+>}]", Hand.getFuuroMentsuList().toString());
    }

    @Test
    @DisplayName("例外を吐くかどうかのテスト")
    void testStringEx() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new hand("123456789m1234s5s").doChii(new tile("4p"), 0, 1));

        assertEquals("その組み合わせではチーできません。", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new hand("123456789m1234s5s").doPon(new tile("4p")));

        assertEquals("その牌ではポンできません。: tile{4p}", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new hand("123456789m1234s5s").doDarkKan(0));

        assertEquals("その牌ではカンできません。: tile{1m}", exception.getMessage());
        hand Hand = new hand("123456789m1244s5s");
        Hand.doPon(new tile("4s"));
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> Hand.doAddKan(0));

        assertEquals("その牌ではカンできません。: tile{1m}", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> new hand("123456789m1234s5s").doLightKan(new tile("4p")));

        assertEquals("その牌ではカンできません。: tile{4p}", exception.getMessage());
    }
}