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
public class HonitsuResolverTest {
    private HonitsuResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> meldList = new ArrayList<>(5);
        meldList.add(new Pair(WEST));
        meldList.add(new Sequence(true, W5));
        meldList.add(new Sequence(true, W5));
        meldList.add(new Triplet(false, EAST));
        meldList.add(new Kong(false, GREEN));
        MeldDirectory comp = new MeldDirectory(meldList, EAST);
        resolver = new HonitsuResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.HONITSU, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}