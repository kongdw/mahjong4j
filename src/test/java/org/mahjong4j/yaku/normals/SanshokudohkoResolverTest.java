package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.SANSHOKUDOHKO;

/**
 * @author yu1ro
 */
public class SanshokudohkoResolverTest {
    private SanshokudohkoResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(RED));
        list.add(new Kong(false, W3));
        list.add(new Kong(false, T3));
        list.add(new Kong(false, D3));
        list.add(new Triplet(true, WHITE));
        MeldDirectory comp = new MeldDirectory(list, GREEN);
        resolver = new SanshokudohkoResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(SANSHOKUDOHKO, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}