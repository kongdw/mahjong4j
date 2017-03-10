package org.mahjong4j.yaku.normals;

import org.junit.Before;
import org.junit.Test;
import org.mahjong4j.hands.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mahjong4j.tile.Tile.*;
import static org.mahjong4j.yaku.normals.NormalYaku.SANKANTSU;

/**
 * @author yu1ro
 */
public class SankantsuResolverTest {
    private SankantsuResolver resolver;

    @Before
    public void setUp() throws Exception {
        List<Meld> list = new ArrayList<>(5);
        list.add(new Pair(RED));
        list.add(new Sequence(false, D2));
        list.add(new Kong(false, GREEN));
        list.add(new Kong(true, WHITE));
        list.add(new Kong(false, WEST));
        MeldDirectory comp = new MeldDirectory(list, D3);
        resolver = new SankantsuResolver(comp);
    }

    @Test
    public void testGetNormalYaku() throws Exception {
        assertEquals(SANKANTSU, resolver.getNormalYaku());
    }

    @Test
    public void testIsMatch() throws Exception {
        assertTrue(resolver.isMatch());
    }
}