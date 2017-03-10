package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class HandsVarArgsTest {
    private Hands actualHands;

    @Before
    public void setUp() throws Exception {
        int[] otherTiles = {
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 1, 1, 1, 0,
            2, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };

        actualHands = new Hands(otherTiles, D7,
            new Triplet(false, T2), new Sequence(true, W7), new Kong(false, WEST));
    }

    @Test
    public void testGetMentsuCompList() throws Exception {
        List<Meld> expectedMeldList = new ArrayList<>(5);
        expectedMeldList.add(new Pair(T1));
        expectedMeldList.add(new Sequence(false, D7));
        expectedMeldList.add(new Sequence(true, W7));
        expectedMeldList.add(new Triplet(false, T2));
        expectedMeldList.add(new Kong(false, WEST));
        MeldDirectory expected = new MeldDirectory(expectedMeldList, D7);

        assertEquals(1, actualHands.getMeldDirectorySet().size());
        assertThat(actualHands.getMeldDirectorySet(), hasItems(expected));
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(actualHands.getCanWin());
    }

    @Test
    public void testGetLast() throws Exception {
        Tile expected = D7;
        assertEquals(expected, actualHands.getLast());
    }

    @Test
    public void testGetHandsComp() throws Exception {
        int[] expected = {
            0, 0, 0, 0, 0, 1, 1, 1, 0,
            0, 0, 0, 0, 0, 1, 1, 1, 0,
            2, 3, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 4, 0,
            0, 0, 0
        };

        assertArrayEquals(expected, actualHands.getHandsComp());
    }
}