package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class RyuisoResolverTest {
    private RyuisoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> meld = new ArrayList<>(5);
        meld.add(new Pair(GREEN));
        meld.add(new Sequence(false, T3));
        meld.add(new Sequence(false, T3));
        meld.add(new Triplet(false, T8));
        meld.add(new Kong(false, T6));
        MeldDirectory comp = new MeldDirectory(meld, T4);
        resolver = new RyuisoResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.RYUISO, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}