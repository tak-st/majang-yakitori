package ckn.yakitori.server;

import org.junit.jupiter.api.Test;

import static ckn.yakitori.server.mountainType.SEQUENCE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class deadTileTest {

    @Test
    void getRinsyan() {
        deadTile DeadTile = new deadTile(new mountainEntity(SEQUENCE));
        assertEquals("1m", DeadTile.getRinsyan().getFullName(), "1");
        assertEquals("1m", DeadTile.getRinsyan().getFullName(), "2");
        assertEquals("2m", DeadTile.getRinsyan().getFullName(), "3");
        assertEquals("3m", DeadTile.getRinsyan().getFullName(), "4");
    }

    @Test
    void getDora() {
        deadTile DeadTile = new deadTile(new mountainEntity(SEQUENCE));
        assertEquals("1m", DeadTile.getDora().getFullName(), "1");
        assertEquals("2m", DeadTile.getDora().getFullName(), "2");
        assertEquals("2m", DeadTile.getDora().getFullName(), "3");
        assertEquals("3m", DeadTile.getDora().getFullName(), "4");
    }

    @Test
    void getHiddenDora() {
        deadTile DeadTile = new deadTile(new mountainEntity(SEQUENCE));
        assertEquals("1m", DeadTile.getHiddenDora().getFullName(), "1");
        assertEquals("2m", DeadTile.getHiddenDora().getFullName(), "2");
        assertEquals("3m", DeadTile.getHiddenDora().getFullName(), "3");
        assertEquals("3m", DeadTile.getHiddenDora().getFullName(), "4");
    }
}