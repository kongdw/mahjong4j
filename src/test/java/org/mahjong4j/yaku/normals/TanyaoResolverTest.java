package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.TANYAO;

/**
 * @author yu1ro
 */
public class TanyaoResolverTest {
    private TanyaoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(W3));
        list.add(new Kong(false, T4));
        list.add(new Sequence(true, D7));
        list.add(new Sequence(false, W3));
        list.add(new Triplet(true, D2));
        MeldDirectory comp = new MeldDirectory(list, D2);
        resolver = new TanyaoResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(TANYAO, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}