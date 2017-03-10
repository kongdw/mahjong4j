package org.mahjong4j.yaku.yakuman;

import org.junit.Before;
import org.junit.Test;
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
public class TsuisoResolverChitoitsuTest {
    private TsuisoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> normalList = new ArrayList<>(5);
        normalList.add(new Pair(EAST));
        normalList.add(new Pair(SOUTH));
        normalList.add(new Pair(WEST));
        normalList.add(new Pair(NORTH));
        normalList.add(new Pair(WHITE));
        normalList.add(new Pair(GREEN));
        normalList.add(new Pair(RED));

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