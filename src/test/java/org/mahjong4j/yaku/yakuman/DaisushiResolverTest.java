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
 * @author knn
 */
public class DaisushiResolverTest {
    private DaisushiResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(W1));
        list.add(new Triplet(true, EAST));
        list.add(new Triplet(true, SOUTH));
        list.add(new Triplet(true, WEST));
        list.add(new Kong(false, NORTH));
        MeldDirectory comp = new MeldDirectory(list, EAST);
        resolver = new DaisushiResolver(comp);
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.DAISUSHI, resolver.getYakuman());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}
