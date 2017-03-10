package org.mahjong4j.hands;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.MahjongTileOverFlowException;
import org.mahjong4j.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author yu1ro
 */
public class PairTest {
    Pair pair1;
    Pair pair2;
    Pair pairF;

    @Before
    public void setUp() throws Exception {
        pair1 = new Pair(Tile.W1);
        pair2 = new Pair(Tile.W1, Tile.W1);
        pairF = new Pair(Tile.W1, Tile.W2);
    }

    @Test
    public void testCheck() throws Exception {
        assertTrue(Pair.check(Tile.D1, Tile.D1));
        assertFalse(Pair.check(Tile.D1, Tile.D4));
    }

    @Test
    public void testGetTile() throws Exception {
        assertEquals(Tile.W1, pair1.getTile());
        assertEquals(Tile.W1, pair2.getTile());
        assertEquals(null, pairF.getTile());
    }

    @Test
    public void testGetIsMentsu() throws Exception {
        assertTrue(pair1.isMeld());
        assertTrue(pair2.isMeld());
        assertFalse(pairF.isMeld());
    }

    @Test
    public void testGetIsOpen() throws Exception {
        assertFalse(pair1.isOpen());
        assertFalse(pair2.isOpen());
        assertFalse(pairF.isOpen());
    }

    @Test
    public void testFindJantoCandidate() throws Exception {
        int[] tiles = {
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            0, 1, 1, 1, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 2, 0
        };
        List<Pair> expected, actual = Pair.findJantoCandidate(tiles);

        assertEquals(1, actual.size());

        expected = new ArrayList<>(7);
        expected.add(new Pair(Tile.WHITE));
        assertEquals(Tile.WHITE, expected.get(0).getTile());
    }


    @Test(expected = MahjongTileOverFlowException.class)
    public void testThrow() throws Exception {
        int[] tiles = {
            0, 2, 3, 4, 5, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Pair.findJantoCandidate(tiles);
    }

    @Test
    public void testEquals() throws Exception {
        assertTrue(pair1.equals(pair2));
        assertFalse(pair1.equals(pairF));
    }

    @After
    public void tearDown() throws Exception {
        assertEquals(pair1.hashCode(), pair2.hashCode());
        assertNotEquals(pair1.hashCode(), pairF.hashCode());

    }
}