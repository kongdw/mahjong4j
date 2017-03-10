package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class SequenceTest {
    private Sequence open;
    private Sequence close;
    private Sequence openTrue;
    private Sequence equal;

    @Before
    public void setUp() throws Exception {
        open = new Sequence(true, W2);
        close = new Sequence(false, W3, W1, W2);
        openTrue = new Sequence(true, D4, D3, D2);
        equal = new Sequence(true, W2, W1, W3);
    }

    @Test
    public void testCheckFalse() throws Exception {
        assertFalse(Sequence.check(D1, W9, D2));
        assertFalse(Sequence.check(D9, T2, T1));
        assertFalse(Sequence.check(T1, T2, T5));
        assertFalse(Sequence.check(WHITE, GREEN, RED));
    }

    @Test
    public void testCheckTrue() throws Exception {
        assertTrue(Sequence.check(W4, W5, W6));
        assertTrue(Sequence.check(T8, T6, T7));
        assertTrue(Sequence.check(D4, D3, D2));
    }

    @Test
    public void testGetTile() throws Exception {
        assertEquals(W2, open.getTile());
        assertEquals(W2, close.getTile());
        assertEquals(D3, openTrue.getTile());
    }

    @Test
    public void testGetIsMentsu() throws Exception {
        assertTrue(open.isMeld());
        assertTrue(close.isMeld());
        assertTrue(openTrue.isMeld());
    }

    @Test
    public void testGetIsOpenT() throws Exception {
        assertTrue(open.isOpen());
        assertTrue(openTrue.isOpen());
    }

    @Test
    public void testGetIsOpenF() throws Exception {
        assertFalse(close.isOpen());
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(open.equals(equal));
        assertFalse(open.equals(close));
        assertFalse(open.equals(openTrue));
    }

    @Test
    public void testHashCode() throws Exception {
        assertEquals(open.hashCode(), equal.hashCode());
        assertNotEquals(open.hashCode(), close.hashCode());
        assertNotEquals(open.hashCode(), openTrue.hashCode());
    }
}