package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Hands;
import org.mahjong4j.hands.MeldDirectory;

import static org.junit.Assert.*;
import static org.mahjong4j.tile.Tile.W1;
import static org.mahjong4j.yaku.yakuman.Yakuman.CHINROTO;

/**
 * @author yu1ro
 */
public class ChinrohtohResolverTest {
    private ChinrohtohResolver chinrohtoh;
    private ChinrohtohResolver notchin;

    @Before
    public void setUp() throws Exception {
        int[] match = {
            3, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0, 0, 0, 0, 0, 3,
            2, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0,
            0, 0, 0
        };

        int[] notMatch = {
            3, 0, 0, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            2, 2, 2, 2, 0, 0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0
        };
        Hands hands = new Hands(match, W1);
        for (MeldDirectory comp : hands.getMeldDirectorySet()) {
            chinrohtoh = new ChinrohtohResolver(comp);
        }
        hands = new Hands(notMatch, W1);
        for (MeldDirectory comp : hands.getMeldDirectorySet()) {
            notchin = new ChinrohtohResolver(comp);
        }
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(CHINROTO, chinrohtoh.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(chinrohtoh.isMatch());
        assertFalse(notchin.isMatch());
    }
}