package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class HandsChitoitsuTest {
    private Hands actual;

    @Before
    public void setUp() throws Exception {
        int[] tiles = {
            2, 2, 0, 2, 2, 0, 2, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 2, 0,
            0, 0, 0
        };

        actual = new Hands(tiles, WEST);
    }

    @Test
    public void testGetMentsuCompList() throws Exception {
        List<Meld> expectedList = new ArrayList<>(7);
        expectedList.add(new Pair(W4));
        expectedList.add(new Pair(WEST));
        expectedList.add(new Pair(W2));
        expectedList.add(new Pair(W1));
        expectedList.add(new Pair(W7));
        expectedList.add(new Pair(T3));
        expectedList.add(new Pair(W5));

        MeldDirectory expected = new MeldDirectory(expectedList, WEST);

        assertEquals(1, actual.getMeldDirectorySet().size());
        assertThat(actual.getMeldDirectorySet(), hasItems(expected));
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(actual.getCanWin());
    }

    @Test
    public void testGetLast() throws Exception {
        assertEquals(WEST, actual.getLast());
    }

    @Test
    public void testGetHandsComp() throws Exception {
        int[] expected = {
            2, 2, 0, 2, 2, 0, 2, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 2, 0,
            0, 0, 0
        };

        assertArrayEquals(expected, actual.getHandsComp());
    }
}