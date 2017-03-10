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
public class ChinitsuResolverTest {
    private ChinitsuResolver chinitsuResolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> meldList = new ArrayList<>(5);
        meldList.add(new Pair(T3));
        meldList.add(new Sequence(true, T2));
        meldList.add(new Sequence(false, T3));
        meldList.add(new Triplet(false, T7));
        meldList.add(new Kong(true, T9));

        MeldDirectory comp = new MeldDirectory(meldList, T2);
        chinitsuResolver = new ChinitsuResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.CHINITSU, chinitsuResolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(chinitsuResolver.isMatch());
    }
}
