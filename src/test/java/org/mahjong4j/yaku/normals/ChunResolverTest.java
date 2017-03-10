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
public class ChunResolverTest {
    private ChunResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> meldList = new ArrayList<>(5);
        meldList.add(new Pair(WHITE));
        meldList.add(new Sequence(true, T3));
        meldList.add(new Sequence(true, D3));
        meldList.add(new Sequence(true, W5));
        meldList.add(new Triplet(false, RED));
        MeldDirectory comp = new MeldDirectory(meldList, W5);
        resolver = new ChunResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.CHUN, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}