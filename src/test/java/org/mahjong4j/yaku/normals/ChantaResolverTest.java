package org.mahjong4j.yaku.normals;

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
public class ChantaResolverTest {
    private ChantaResolver chantaResolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> matchList = new ArrayList<>(5);
        matchList.add(new Pair(SOUTH));
        matchList.add(new Sequence(true, W8));
        matchList.add(new Sequence(true, T2));
        matchList.add(new Triplet(false, D1));
        matchList.add(new Kong(false, WEST));

        MeldDirectory match = new MeldDirectory(matchList, T3);
        chantaResolver = new ChantaResolver(match);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.CHANTA, chantaResolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(chantaResolver.isMatch());
    }
}