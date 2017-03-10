package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.SHOSANGEN;

/**
 * @author yu1ro
 */
public class ShosangenResolverTest {
    private ShosangenResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(RED));
        list.add(new Sequence(true, T2));
        list.add(new Sequence(false, D2));
        list.add(new Kong(false, GREEN));
        list.add(new Triplet(true, WHITE));
        MeldDirectory comp = new MeldDirectory(list, GREEN);
        resolver = new ShosangenResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(SHOSANGEN, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}