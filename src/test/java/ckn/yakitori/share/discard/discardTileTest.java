package ckn.yakitori.share.discard;

import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class discardTileTest {

    @Test
    void getCalledRiichi() {
        discardTile tile = new discardTile(new tile("1m"), false, false, true);
        assertFalse(tile.getCalledRiichi());
    }

    @Test
    void isImmediately() {
        discardTile tile = new discardTile(new tile("1m"), true, false, true);
        assertFalse(tile.isImmediately());
    }

    @Test
    void isStealed() {
        discardTile tile = new discardTile(new tile("1m"), true, false, true);
        assertTrue(tile.isStealed());
    }
}