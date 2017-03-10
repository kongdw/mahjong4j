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
public class HandsListTest {
    private Hands actualHands;

    @Before
    public void setUp() throws Exception {
        int[] otherTiles = {
                0, 0, 1, 1, 1, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                2, 0, 0, 0,
                0, 0, 0
        };
        List<Meld> meldList = new ArrayList<>(2);
        meldList.add(new Triplet(true, D4));
        meldList.add(new Kong(true, RED));

        actualHands = new Hands(otherTiles, EAST, meldList);
    }

    @Test
    public void testGetMentsuCompList() throws Exception {
        List<Meld> expectedMeldList = new ArrayList<>(5);
        expectedMeldList.add(new Pair(EAST));
        expectedMeldList.add(new Sequence(false, W4));
        expectedMeldList.add(new Sequence(false, D4));
        expectedMeldList.add(new Triplet(true, D4));
        expectedMeldList.add(new Kong(true, RED));
        MeldDirectory expected = new MeldDirectory(expectedMeldList, EAST);

        assertEquals(1, actualHands.getMeldDirectorySet().size());
        assertThat(actualHands.getMeldDirectorySet(), hasItems(expected));
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(actualHands.getCanWin());
    }

    @Test
    public void testGetLast() throws Exception {
        Tile expected = EAST;
        assertEquals(expected, actualHands.getLast());
    }

    @Test
    public void testGetHandsComp() throws Exception {
        int[] expected = {
                0, 0, 1, 1, 1, 0, 0, 0, 0,
                0, 0, 1, 4, 1, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                2, 0, 0, 0,
                0, 0, 4
        };

        assertArrayEquals(expected, actualHands.getHandsComp());
    }
    @Test
    public void testCanWin() throws Exception{
        for (int i = 0; i < 100000; i++) {
            int[] otherTiles = {
                    3, 4, 4, 3, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0
            };
            Hands hands = new Hands(otherTiles,W1);
            System.out.println(hands.getMeldDirectorySet());
            assertTrue(hands.getCanWin());
        }
    }
}