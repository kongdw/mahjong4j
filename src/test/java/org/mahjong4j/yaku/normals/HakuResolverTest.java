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
 * @author knn
 */
public class HakuResolverTest {
    private HakuResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> meldList = new ArrayList<>(5);
        meldList.add(new Pair(GREEN));
        meldList.add(new Sequence(true, T3));
        meldList.add(new Sequence(true, D3));
        meldList.add(new Sequence(true, W5));
        meldList.add(new Triplet(false, WHITE));
        MeldDirectory comp = new MeldDirectory(meldList, W5);
        resolver = new HakuResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.HAKU, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}