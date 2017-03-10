package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.RED;

/**
 * @author yu1ro
 */
public class HandsKokushimusoTest {
    private Hands actual;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 2, 1, 1,
            1, 1, 1,
        };

        actual = new Hands(tiles, RED);
    }

    @Test
    public void testGetMentsuCompList() throws Exception {
        assertEquals(0, actual.getMeldDirectorySet().size());
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(actual.getCanWin());
    }

    @Test
    public void testGetLast() throws Exception {
        assertEquals(RED, actual.getLast());
    }

    @Test
    public void testGetHandsComp() throws Exception {
        int[] expected = {
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 0, 0, 0, 0, 0, 0, 0, 1,
            1, 2, 1, 1,
            1, 1, 1,
        };

        assertArrayEquals(expected, actual.getHandsComp());
    }
}