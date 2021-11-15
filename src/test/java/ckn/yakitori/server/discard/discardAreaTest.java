package ckn.yakitori.server.discard;

import ckn.yakitori.share.tile.tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class discardAreaTest {

    @Test
    void addDiscardTile() {
        discardArea DiscardArea = new discardArea();
        DiscardArea.addDiscardTile(new discardTile(new tile("1m")));
        assertEquals("1m", DiscardArea.getDiscardList(0).getTile().getFullName(), "1");

    }

    @Test
    void check() {
        List<Integer> expected = new ArrayList<>(List.of(0));
        discardArea DiscardArea = new discardArea();
        DiscardArea.addDiscardTile(new discardTile(new tile("1m")));
        DiscardArea.addDiscardTile(new discardTile(new tile("2m")));
        DiscardArea.addDiscardTile(new discardTile(new tile("3m")));
        DiscardArea.addDiscardTile(new discardTile(new tile("4m")));
        DiscardArea.addDiscardTile(new discardTile(new tile("5m")));
        DiscardArea.addDiscardTile(new discardTile(new tile("6m")));
        assertIterableEquals(expected, DiscardArea.check(new tile("1m")));
        DiscardArea.addDiscardTile(new discardTile(new tile("1m")));
        DiscardArea.addDiscardTile(new discardTile(new tile("1m")));
        expected.add(6);
        expected.add(7);
        assertIterableEquals(expected, DiscardArea.check(new tile("1m")));
    }

    @Test
    void nagashicheck() {
        discardArea DiscardArea = new discardArea();
        DiscardArea.addDiscardTile(new discardTile(new tile("1z")));
        assertTrue(DiscardArea.Nagashicheck(), "1");


        DiscardArea.addDiscardTile(new discardTile(new tile("2m")));
        assertFalse(DiscardArea.Nagashicheck(), "1");
    }
}