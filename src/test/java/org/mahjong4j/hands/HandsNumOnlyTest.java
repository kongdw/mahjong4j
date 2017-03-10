package org.mahjong4j.hands;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.tile.Tile;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.W6;

/**
 * @author yu1ro
 */
public class HandsNumOnlyTest {
    Hands hands;

    @Before
    public void setUp() throws Exception {
        int[] tiles = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 1, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 2, 0
        };
        Tile last = W6;
        hands = new Hands(tiles, last);
    }

    @Test
    public void testFindMentsu() throws Exception {
        Set<MeldDirectory> winSetList = hands.getMeldDirectorySet();
        assertEquals(1, winSetList.size());
    }

    @Test
    public void testWinMentsuList() throws Exception {
        Set<MeldDirectory> winSetList = hands.getMeldDirectorySet();
        for (MeldDirectory winMentsuSet : winSetList) {
            assertEquals(0, winMentsuSet.getKantsuCount());
            assertEquals(0, winMentsuSet.getKotsuCount());
            assertEquals(4, winMentsuSet.getShuntsuCount());
            assertEquals(Tile.GREEN, winMentsuSet.getPairList().get(0).getTile());
        }
    }

    @Test
    public void testGetCanWin() throws Exception {
        assertTrue(hands.getCanWin());
    }

    @Test
    public void testGetLast() throws Exception {
        assertEquals(W6, hands.getLast());
    }

    @Test
    public void testGetHandsComp() throws Exception {
        int[] expected = new int[]{
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 1, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 2, 0
        };

        assertArrayEquals(expected, hands.getHandsComp());
    }
}