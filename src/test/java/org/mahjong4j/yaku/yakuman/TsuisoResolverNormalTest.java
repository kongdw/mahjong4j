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
public class TsuisoResolverNormalTest {
    private TsuisoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> normalList = new ArrayList<>(5);
        normalList.add(new Pair(SOUTH));
        normalList.add(new Kong(false, WEST));
        normalList.add(new Triplet(true, WHITE));
        normalList.add(new Triplet(false, GREEN));
        normalList.add(new Kong(true, EAST));
        MeldDirectory normal = new MeldDirectory(normalList, WEST);
        resolver = new TsuisoResolver(normal);
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }

    @Test
    public void testGetYakuman() throws Exception {
        assertEquals(Yakuman.TSUISO, resolver.getYakuman());
    }
}