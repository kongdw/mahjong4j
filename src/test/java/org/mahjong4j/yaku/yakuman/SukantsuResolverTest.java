package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Kong;
import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.yakuman.Yakuman.SUKANTSU;

/**
 * @author yu1ro
 */
public class SukantsuResolverTest {
    private SukantsuResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> meld = new ArrayList<>(5);
        meld.add(new Pair(W3));
        meld.add(new Kong(false, W2));
        meld.add(new Kong(true, T5));
        meld.add(new Kong(true, D9));
        meld.add(new Kong(false, WEST));
        MeldDirectory comp = new MeldDirectory(meld, W3);
        resolver = new SukantsuResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(SUKANTSU, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}