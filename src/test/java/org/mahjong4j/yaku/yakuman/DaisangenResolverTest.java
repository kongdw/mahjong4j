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
public class DaisangenResolverTest {
    private DaisangenResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(W1));
        list.add(new Sequence(true, T3));
        list.add(new Triplet(true, RED));
        list.add(new Triplet(true, WHITE));
        list.add(new Kong(false, GREEN));
        MeldDirectory comp = new MeldDirectory(list, WHITE);
        resolver = new DaisangenResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.DAISANGEN, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}
