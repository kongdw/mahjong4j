package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class TripletTest {
    private Triplet open;
    private Triplet close;
    private Triplet closeFls;
    private Triplet equal;

    @Before
    public void setUp() throws Exception {
        open = new Triplet(true, EAST);
        close = new Triplet(false, WEST, WEST, WEST);
        closeFls = new Triplet(false, WHITE, WHITE, RED);
        equal = new Triplet(true, EAST, EAST, EAST);
    }

    @Test
    public void testCheckTrue() throws Exception {
        assertTrue(Triplet.check(T2, T2, T2));
        assertTrue(Triplet.check(WHITE, WHITE, WHITE));
        assertTrue(Triplet.check(SOUTH, SOUTH, SOUTH));
    }

    @Test
    public void testCheckFalse() throws Exception {
        assertFalse(Triplet.check(D2, D2, D4));
        assertFalse(Triplet.check(WHITE, RED, RED));
        assertFalse(Triplet.check(EAST, SOUTH, WEST));
    }

    @Test
    public void testGetTile() throws Exception {
        assertEquals(EAST, open.getTile());
        assertEquals(WEST, close.getTile());
        assertNull(closeFls.getTile());
    }

    @Test
    public void testGetIsMentsu() throws Exception {
        assertTrue(open.isMeld());
        assertTrue(close.isMeld());
        assertFalse(closeFls.isMeld());
    }

    @Test
    public void testGetIsOpen() throws Exception {
        assertTrue(open.isOpen());
        assertFalse(close.isOpen());
        assertFalse(closeFls.isOpen());
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(open.equals(equal));
        assertFalse(open.equals(close));
        assertFalse(open.equals(closeFls));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(open.hashCode(), equal.hashCode());
        assertNotEquals(open.hashCode(), close.hashCode());
        assertNotEquals(open.hashCode(), closeFls.hashCode());
    }
}