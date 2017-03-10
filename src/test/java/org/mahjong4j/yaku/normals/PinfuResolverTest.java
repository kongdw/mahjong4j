package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.Meld;
import org.mahjong4j.hands.MeldDirectory;
import org.mahjong4j.hands.Sequence;
import org.mahjong4j.hands.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;

/**
 * @author yu1ro
 */
public class PinfuResolverTest {

    private PinfuResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> meldList = new ArrayList<>(5);
        meldList.add(new Pair(W9));
        meldList.add(new Sequence(false, T2));
        meldList.add(new Sequence(false, T3));
        meldList.add(new Sequence(false, D3));
        meldList.add(new Sequence(false, W5));

        MeldDirectory comp = new MeldDirectory(meldList, T3);
        resolver = new PinfuResolver(comp, null, null);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(NormalYaku.PINFU, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}