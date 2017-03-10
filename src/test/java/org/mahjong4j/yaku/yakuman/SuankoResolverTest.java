package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Triplet;
import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class SuankoResolverTest {
    private SuankoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> meld = new ArrayList<>(5);
        meld.add(new Pair(W3));
        meld.add(new Triplet(false, W2));
        meld.add(new Triplet(false, T5));
        meld.add(new Triplet(false, D9));
        meld.add(new Triplet(false, WEST));
        MeldDirectory comp = new MeldDirectory(meld, D9);
        resolver = new SuankoResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.SUANKO, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}