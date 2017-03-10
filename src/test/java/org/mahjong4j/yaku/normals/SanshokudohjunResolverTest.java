package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.SANSHOKUDOHJUN;

/**
 * @author yu1ro
 */
public class SanshokudohjunResolverTest {
    private SanshokudohjunResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(RED));
        list.add(new Sequence(false, D2));
        list.add(new Sequence(false, W2));
        list.add(new Sequence(false, T2));
        list.add(new Triplet(false, WHITE));
        MeldDirectory comp = new MeldDirectory(list, T1);
        resolver = new SanshokudohjunResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(SANSHOKUDOHJUN, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}