package ckn.yakitori.share.mentsu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ckn.yakitori.share.mentsu.WaitType.RYANMEN;
import static ckn.yakitori.share.mentsu.WaitType.TANKI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WaitTypeTest {

    @Test
    @DisplayName("符数のテスト")
    void getFu() {
        assertEquals(2, TANKI.getFu());
        assertEquals(0, RYANMEN.getFu());
    }
}